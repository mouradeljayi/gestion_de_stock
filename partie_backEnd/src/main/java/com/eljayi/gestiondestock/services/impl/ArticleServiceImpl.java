package com.eljayi.gestiondestock.services.impl;

import com.eljayi.gestiondestock.dto.ArticleDto;
import com.eljayi.gestiondestock.dto.LigneCommandeClientDto;
import com.eljayi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.eljayi.gestiondestock.dto.LigneVenteDto;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.model.Article;
import com.eljayi.gestiondestock.repository.ArticleRepository;
import com.eljayi.gestiondestock.repository.LigneCommandeClientRepository;
import com.eljayi.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.eljayi.gestiondestock.repository.LigneVenteRepository;
import com.eljayi.gestiondestock.services.ArticleService;
import com.eljayi.gestiondestock.validator.ArticleValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Cet article n'est pas valide {}", dto);
            throw new InvalidEntityException("Cet article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        Article savedArticle = articleRepository.save(ArticleDto.toEntity(dto));
        return ArticleDto.fromEntity(savedArticle);
    }

    @Override
    public ArticleDto findbyId(Integer id)
    {
        if(id == null)
        {
            log.error("ID de l'article est null");
            return null;
        }
        return articleRepository.findById(id)
            .map(ArticleDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Aucuun article avec l'ID = " + id + " n'été trouvé dans la BDD",
                    ErrorCodes.ARTICLE_NOT_FOUND
            ));
    }

    @Override
    public ArticleDto findbyCodeArticle(String codeArticle)
    {
        if(!StringUtils.hasLength(codeArticle))
        {
            log.error("Code de l'article est null");
            return null;
        }
        return articleRepository.findArticleByCodeArticle(codeArticle)
                .map(ArticleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun article avec l'ID = " + codeArticle + " n'été trouvé dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND
                ));
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
        return ligneVenteRepository.findAllByArticleId(idArticle).stream()
                .map(LigneVenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return ligneCommandeClientRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return ligneCommandeFournisseurRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticlesByIdCategory(Integer idCategory) {
        return articleRepository.findAllByCategorieId(idCategory).stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAll()
    {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("ID de l'article est null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
