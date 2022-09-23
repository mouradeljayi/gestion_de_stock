package com.eljayi.gestiondestock.services.impl;

import com.eljayi.gestiondestock.dto.*;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.model.*;
import com.eljayi.gestiondestock.repository.*;
import com.eljayi.gestiondestock.services.CommandeFournisseurService;
import com.eljayi.gestiondestock.services.MouvementStockService;
import com.eljayi.gestiondestock.validator.ArticleValidator;
import com.eljayi.gestiondestock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    private MouvementStockService mouvementStockService;

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande fournisseur n'est pas valide");
            throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found int DB", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID " + dto.getFournisseur().getId() + " n'a été trouvé dans le BDD" , ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligne -> {
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

        CommandeFournisseur savedCmdClt = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligne -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligne);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdClt);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);

        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("Etat Commande is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec un état null ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE
            );
        }
        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
        commandeFournisseur.setEtatCommande(etatCommande);
        CommandeFournisseur savedCommande =  commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur));

        if (commandeFournisseur.isCommandeLivree()) {
            updateMouvementStock(idCommande);
        }

        return CommandeFournisseurDto.fromEntity(savedCommande);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
            log.error("quantite is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec une ligne de commande null ou égale à ZERO ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE
            );
        }
        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

        LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurOptional.orElse(null);
        assert ligneCommandeFournisseur != null;
        ligneCommandeFournisseur.setQuantite(quantite);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

        return commandeFournisseur;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        checkIdCommande(idCommande);
        if (idFournisseur == null) {
            log.error("ID Fournisseur is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec un ID fournisseur null ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE
            );
        }

        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

        Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
        if (fournisseurOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID " + idFournisseur + " n'a été trouvé dans le BDD" , ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        commandeFournisseur.setFournisseur(FournisseurDto.fromEntity(fournisseurOptional.get()));

        return CommandeFournisseurDto.fromEntity(
                commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur))
        );
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle);

        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseur = findLigneCommandeFournisseur(idLigneCommande);

        Optional<Article> articleOptional = articleRepository.findById(idArticle);
        if (articleOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucune article avec l'ID " + idArticle + " n'a été trouvé dans le BDD" , ErrorCodes.ARTICLE_NOT_FOUND);
        }

        List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        LigneCommandeFournisseur ligneCommandeFournisseurToSave = ligneCommandeFournisseur.orElse(null);
        assert ligneCommandeFournisseurToSave != null;
        ligneCommandeFournisseurToSave.setArticle(articleOptional.get());
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSave);

        return commandeFournisseur;
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
        // to check the LigneCommandFournisseur and inform the user in case it is absent
        findLigneCommandeFournisseur(idLigneCommande);
        ligneCommandeFournisseurRepository.deleteById(idLigneCommande);

        return commandeFournisseur;
    }

    @Override
    public CommandeFournisseurDto findbyId(Integer id) {
        if (id == null) {
            log.error("Commande fournisseur ID is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a été trouvée avec l'ID " + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Commande fournisseur code is NULL");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a été trouvée avec le code " + code, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
        return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande).stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande fournisseur ID is null");
            return;
        }
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(id);
        if (!ligneCommandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une commande fournisseur déja utilisée", ErrorCodes.COMMANDE_FOURNISSEUR_ALREADY_IN_USE);
        }
        commandeFournisseurRepository.deleteById(id);
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
                    "Impossible de modifier l'etat commande avec un ID null ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE
            );
        }
    }

    private CommandeFournisseurDto checkEtatCommande(Integer idCommande) {
        CommandeFournisseurDto commandeFournisseur = findbyId(idCommande);
        if (commandeFournisseur.isCommandeLivree()) {
            throw  new InvalidOperationException(
                    "Impossible de modifier la commande lorsqu'elle est livrée ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE
            );
        }
        return commandeFournisseur;
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null) {
            log.error("ID Ligne Commande is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec une ligne de commande null ", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE
            );
        }
    }

    private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(Integer idLigneCommande) {
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =  ligneCommandeFournisseurRepository.findById(idLigneCommande);
        if (ligneCommandeFournisseurOptional.isEmpty()) {
            throw new EntityNotFoundException("Aucune ligne commande avec l'ID " + idLigneCommande + " n'a été trouvée dans le BDD" , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
        return ligneCommandeFournisseurOptional;
    }

    private void checkIdArticle(Integer idArticle) {
        if (idArticle == null) {
            log.error("ID Article is null");
            throw  new InvalidOperationException(
                    "Impossible de modifier l'etat commande avec un ID article null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE
            );
        }
    }

    private void updateMouvementStock(Integer idCommande) {
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);

        ligneCommandeFournisseurs.forEach(ligne -> {
            MouvementStockDto mouvementStockDto = MouvementStockDto.builder()
                    .article(ArticleDto.fromEntity(ligne.getArticle()))
                    .dateMvt(Instant.now())
                    .typeMvtStock(TypeMvtStock.ENTREE)
                    .sourceMvt(SourceMvtStock.COMMANDE_FOURNISSEUR)
                    .quantite(ligne.getQuantite())
                    .idEntreprise(ligne.getIdEntreprise())
                    .build();
            mouvementStockService.entreeStock(mouvementStockDto);
        });
    }
}
