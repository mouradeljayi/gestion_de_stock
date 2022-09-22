package com.eljayi.gestiondestock.services;

import com.eljayi.gestiondestock.dto.ArticleDto;
import com.eljayi.gestiondestock.dto.LigneCommandeClientDto;
import com.eljayi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.eljayi.gestiondestock.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto dto);
    ArticleDto findbyId(Integer id);
    ArticleDto findbyCodeArticle(String codeArticle);
    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);
    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);
    List<ArticleDto> findAllArticlesByIdCategory(Integer idCategory);
    List<ArticleDto> findAll();
    void delete(Integer id);
}
