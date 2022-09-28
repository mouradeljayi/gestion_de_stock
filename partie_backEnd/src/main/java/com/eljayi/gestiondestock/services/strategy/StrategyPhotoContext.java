package com.eljayi.gestiondestock.services.strategy;

import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StrategyPhotoContext {

    private BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;



    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determineContext(context);
        return strategy.savePhoto(id, photo, title);
    }

    private void determineContext(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "fournisseur" -> strategy = beanFactory.getBean(beanName, SaveFournisseurPhoto.class);
            case "client" -> strategy = beanFactory.getBean(beanName, SaveClientPhoto.class);
            case "article" -> strategy = beanFactory.getBean(beanName, SaveArticlePhoto.class);
            case "utilisateur" -> strategy = beanFactory.getBean(beanName, SaveUtilisateurPhoto.class);
            case "entreprise" -> strategy = beanFactory.getBean(beanName, SaveEntreprisePhoto.class);

            default -> throw new InvalidOperationException("Contexte Inconnue", ErrorCodes.UNKNOWN_CONTEXT);
        }
    }
}
