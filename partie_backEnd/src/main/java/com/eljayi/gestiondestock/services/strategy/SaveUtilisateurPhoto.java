package com.eljayi.gestiondestock.services.strategy;

import com.eljayi.gestiondestock.dto.UtilisateurDto;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.services.FlickrService;
import com.eljayi.gestiondestock.services.UtilisateurService;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("utilisateurStrategy")
@AllArgsConstructor
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto>{

    private final FlickrService flickrService;
    private final UtilisateurService utilisateurService;

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        UtilisateurDto utilisateur = utilisateurService.findbyId(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregisterement de la photo de l'utilisateur", ErrorCodes.UPADTE_PHOTO_EXCEPTION);
        }
        utilisateur.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateur);
    }
}
