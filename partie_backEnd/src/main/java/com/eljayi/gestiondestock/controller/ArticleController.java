package com.eljayi.gestiondestock.controller;


import com.eljayi.gestiondestock.controller.api.ArticleApi;
import com.eljayi.gestiondestock.dto.ArticleDto;
import com.eljayi.gestiondestock.dto.LigneCommandeClientDto;
import com.eljayi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.eljayi.gestiondestock.dto.LigneVenteDto;
import com.eljayi.gestiondestock.services.ArticleService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {
    private final ArticleService articleService;

    public ArticleController( ArticleService articleService ) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findbyId(Integer id) {
        return articleService.findbyId(id);
    }

    @Override
    public ArticleDto findbyCodeArticle(String codeArticle) {
        return articleService.findbyCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
        return articleService.findHistoriqueVentes(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return articleService.findHistoriqueCommandeClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandeFournisseur(idArticle);
    }

    @Override
    public List<ArticleDto> findAllArticlesByIdCategory(Integer idCategory) {
        return articleService.findAllArticlesByIdCategory(idCategory);
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);

    }
}
