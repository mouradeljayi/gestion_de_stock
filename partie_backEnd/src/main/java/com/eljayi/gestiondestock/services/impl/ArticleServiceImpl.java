package com.eljayi.gestiondestock.services.impl;

import com.eljayi.gestiondestock.dto.ArticleDto;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.model.Article;
import com.eljayi.gestiondestock.repository.ArticleRepository;
import com.eljayi.gestiondestock.services.ArticleService;
import com.eljayi.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

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
        Optional<Article> article = articleRepository.findById(id);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec l'ID " + id + "n'est trouvé dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }

    @Override
    public ArticleDto findbyCodeArticle(String codeArticle)
    {
        if(!StringUtils.hasLength(codeArticle))
        {
            log.error("Code de l'article est null");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec le code " + codeArticle + "n'est trouvé dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND)
        );
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
