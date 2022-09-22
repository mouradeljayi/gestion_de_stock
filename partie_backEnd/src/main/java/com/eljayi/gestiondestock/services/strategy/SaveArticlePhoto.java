package com.eljayi.gestiondestock.services.strategy;

import com.eljayi.gestiondestock.dto.ArticleDto;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.services.ArticleService;
import com.eljayi.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("articleStrategy")
@AllArgsConstructor
public class SaveArticlePhoto implements Strategy<ArticleDto>{


    private final FlickrService flickrService;
    private final ArticleService articleService;

    @Override
    public ArticleDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ArticleDto article = articleService.findbyId(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregisterement de la photo de l'article", ErrorCodes.UPADTE_PHOTO_EXCEPTION);
        }
        article.setPhoto(urlPhoto);
        return articleService.save(article);

    }
}
