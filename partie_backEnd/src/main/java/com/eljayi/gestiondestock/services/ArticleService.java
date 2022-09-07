package com.eljayi.gestiondestock.services;

import com.eljayi.gestiondestock.dto.ArticleDto;
import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto dto);

    ArticleDto findbyId(Integer id);

    ArticleDto findbyCodeArticle(String codeArticle);

    List<ArticleDto> findAll();

    void delete(Integer id);
}
