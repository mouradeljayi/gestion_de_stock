package com.eljayi.gestiondestock.services.strategy;

import com.eljayi.gestiondestock.dto.FournisseurDto;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.services.FlickrService;
import com.eljayi.gestiondestock.services.FournisseurService;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("fournisseurStrategy")
@AllArgsConstructor
public class SaveFournisseurPhoto implements Strategy<FournisseurDto>{

    private final FlickrService flickrService;
    private final FournisseurService fournisseurService;

    @Override
    public FournisseurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        FournisseurDto fournisseur = fournisseurService.findbyId(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregisterement de la photo du fournisseur", ErrorCodes.UPADTE_PHOTO_EXCEPTION);
        }
        fournisseur.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseur);
    }
}
