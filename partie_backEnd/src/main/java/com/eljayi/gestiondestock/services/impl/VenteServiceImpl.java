package com.eljayi.gestiondestock.services.impl;

import com.eljayi.gestiondestock.dto.ArticleDto;
import com.eljayi.gestiondestock.dto.MouvementStockDto;
import com.eljayi.gestiondestock.dto.VenteDto;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.model.*;
import com.eljayi.gestiondestock.repository.ArticleRepository;
import com.eljayi.gestiondestock.repository.LigneVenteRepository;
import com.eljayi.gestiondestock.repository.VenteRepository;
import com.eljayi.gestiondestock.services.MouvementStockService;
import com.eljayi.gestiondestock.services.VenteService;
import com.eljayi.gestiondestock.validator.VenteValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class VenteServiceImpl implements VenteService {

    private ArticleRepository articleRepository;
    private VenteRepository venteRepository;
    private LigneVenteRepository ligneVenteRepository;

    private MouvementStockService mouvementStockService;

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Cette vente n'est pas valide {}", dto);
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        dto.getLigneVentes().forEach(ligne -> {
            Optional<Article> article = articleRepository.findById(ligne.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("l'article avec l'ID " + ligne.getArticle().getId() + " n'existe pas");
            }
        });
        if(!articleErrors.isEmpty()){
            log.error("One or more articles were not found in the DB {}", errors);
            throw new InvalidEntityException("Un article ou plusieurs n'ont pas été trouvé dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        Vente savedVente =  venteRepository.save(VenteDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneDto -> {
            LigneVente ligneVente = ligneDto.toEntity(ligneDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
            updateMouvementStock(ligneVente);
        });

        return VenteDto.fromEntity(savedVente);

    }

    @Override
    public VenteDto findbyId(Integer id) {
        if (id == null) {
            log.error("Vente ID is null");
            return null;
        }
        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun vente n'a été trouvé avec l'ID " + id, ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VenteDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Vente code is NULL");
            return null;
        }
        return venteRepository.findVenteByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun vente n'a été trouvé avec le code " + code, ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Vente ID is null");
            return;
        }
        venteRepository.deleteById(id);
    }

    private void updateMouvementStock(LigneVente ligne) {
        MouvementStockDto mouvementStockDto = MouvementStockDto.builder()
                .article(ArticleDto.fromEntity(ligne.getArticle()))
                .dateMvt(Instant.now())
                .typeMvtStock(TypeMvtStock.SORTIE)
                .sourceMvt(SourceMvtStock.VENTE)
                .quantite(ligne.getQuantite())
                .idEntreprise(ligne.getIdEntreprise())
                .build();
        mouvementStockService.sortieStock(mouvementStockDto);
    }
}
