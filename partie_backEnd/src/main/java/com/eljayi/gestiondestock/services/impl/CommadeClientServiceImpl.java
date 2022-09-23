package com.eljayi.gestiondestock.services.impl;

import com.eljayi.gestiondestock.dto.*;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.model.*;
import com.eljayi.gestiondestock.repository.ArticleRepository;
import com.eljayi.gestiondestock.repository.ClientRepository;
import com.eljayi.gestiondestock.repository.CommandeClientRepository;
import com.eljayi.gestiondestock.repository.LigneCommandeClientRepository;
import com.eljayi.gestiondestock.services.CommandeClientService;
import com.eljayi.gestiondestock.services.MouvementStockService;
import com.eljayi.gestiondestock.validator.ArticleValidator;
import com.eljayi.gestiondestock.validator.CommandeClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CommadeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    private MouvementStockService mouvementStockService;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        if (dto.getId() != null && dto.isCommandeLivree()) {
            throw  new InvalidOperationException(
                    "Impossible de modifier la commande lorsqu'elle est livrée ", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with ID {} was not found int DB", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID " + dto.getClient().getId() + " n'a été trouvé dans le BDD" , ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligne -> {
                if(ligne.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligne.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("l'article avec l'ID " + ligne.getArticle().getId() + " n'existe pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }
        if (!articleErrors.isEmpty()) {
            log.warn("An article was not found");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligne -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligne);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findbyId(Integer id) {
        if (id == null) {
            log.error("Commande client ID is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvée avec l'ID " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Commande client code is NULL");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvée avec le code " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande client ID is null");
            return;
        }
        commandeClientRepository.deleteById(id);

    }

    @Override
    public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
        return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {

        checkIdCommande(idCommande);

        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("Etat Commande is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec un état null ", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }
        CommandeClientDto commandeClient = checkEtatCommande(idCommande);
        commandeClient.setEtatCommande(etatCommande);
        CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient));

        if (commandeClient.isCommandeLivree()) {
            updateMouvementStock(idCommande);
        }

        return CommandeClientDto.fromEntity(savedCommandeClient);
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
            log.error("quantite is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec une ligne de commande null ou égale à ZERO ", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }
        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLignCommandeClient(idLigneCommande);

        LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.orElse(null);
        assert ligneCommandeClient != null;
        ligneCommandeClient.setQuantite(quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);

        return commandeClient;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {

        checkIdCommande(idCommande);
        if (idClient == null) {
            log.error("ID Client is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec un ID client null ", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }

        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucun client avec l'ID " + idClient + " n'a été trouvé dans le BDD" , ErrorCodes.CLIENT_NOT_FOUND);
        }

        commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));

        return CommandeClientDto.fromEntity(
                commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
        );

    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle);

        CommandeClientDto commandeClient = checkEtatCommande(idCommande);
        Optional<LigneCommandeClient> ligneCommandeClient = findLignCommandeClient(idLigneCommande);

        Optional<Article> articleOptional = articleRepository.findById(idArticle);
        if (articleOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucune article avec l'ID " + idArticle + " n'a été trouvé dans le BDD" , ErrorCodes.ARTICLE_NOT_FOUND);
        }

        List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        LigneCommandeClient ligneCommandeClientToSave = ligneCommandeClient.orElse(null);
        assert ligneCommandeClientToSave != null;
        ligneCommandeClientToSave.setArticle(articleOptional.get());
        ligneCommandeClientRepository.save(ligneCommandeClientToSave);

        return commandeClient;
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClient = checkEtatCommande(idCommande);
        // to check the LigneCommandClient and inform the user in case it is absent
        findLignCommandeClient(idLigneCommande);
        ligneCommandeClientRepository.deleteById(idLigneCommande);

        return commandeClient;

    }

    //**
    //
    // [ CHECK METHODS ]
    //
    // **

    private void checkIdCommande(Integer idCommande) {
        if (idCommande == null) {
            log.error("Commande client ID is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec un ID null ", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }
    }

    private void checkIdArticle(Integer idArticle) {
        if (idArticle == null) {
            log.error("ID Article is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec un ID article null", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null) {
            log.error("ID Ligne Commande is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec une ligne de commande null ", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }
    }

    private CommandeClientDto checkEtatCommande(Integer idCommande) {
        CommandeClientDto commandeClient = findbyId(idCommande);
        if (commandeClient.isCommandeLivree()) {
            throw  new InvalidOperationException(
                    "Impossible de modifier la commande lorsqu'elle est livrée ", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE
            );
        }
        return commandeClient;
    }

    private Optional<LigneCommandeClient> findLignCommandeClient(Integer idLigneCommande) {
        Optional<LigneCommandeClient> ligneCommandeClientOptional =  ligneCommandeClientRepository.findById(idLigneCommande);
        if (ligneCommandeClientOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucune ligne commande avec l'ID " + idLigneCommande + " n'a été trouvée dans le BDD" , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }
        return ligneCommandeClientOptional;
    }

    private void updateMouvementStock(Integer idCommande) {
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);

        ligneCommandeClients.forEach(ligne -> {
            MouvementStockDto mouvementStockDto = MouvementStockDto.builder()
                    .article(ArticleDto.fromEntity(ligne.getArticle()))
                    .dateMvt(Instant.now())
                    .typeMvtStock(TypeMvtStock.SORTIE)
                    .sourceMvt(SourceMvtStock.COMMANDE_CLIENT)
                    .quantite(ligne.getQuantite())
                    .idEntreprise(ligne.getIdEntreprise())
                    .build();
            mouvementStockService.sortieStock(mouvementStockDto);
        });
    }

}
