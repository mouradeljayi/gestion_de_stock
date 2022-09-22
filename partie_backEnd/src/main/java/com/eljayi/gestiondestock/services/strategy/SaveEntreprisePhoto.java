package com.eljayi.gestiondestock.services.strategy;

import com.eljayi.gestiondestock.dto.EntrepriseDto;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.services.EntrepriseService;
import com.eljayi.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("entrepriseStrategy")
@AllArgsConstructor
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto>{

    private final FlickrService flickrService;
    private final EntrepriseService entrepriseService;

    @Override
    public EntrepriseDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        EntrepriseDto entreprise = entrepriseService.findbyId(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregisterement de la photo de l'entreprise", ErrorCodes.UPADTE_PHOTO_EXCEPTION);
        }
        entreprise.setPhoto(urlPhoto);
        return entrepriseService.save(entreprise);
    }
}
