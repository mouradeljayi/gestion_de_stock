package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findArticleByCodeArticle (String codeArticle);
    List<Article> findAllByCategorieId(Integer idCategory);
}
