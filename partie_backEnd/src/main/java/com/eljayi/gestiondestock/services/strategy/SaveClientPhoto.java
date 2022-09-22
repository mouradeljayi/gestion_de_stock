package com.eljayi.gestiondestock.services.strategy;

import com.eljayi.gestiondestock.dto.ClientDto;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.services.ClientService;
import com.eljayi.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("clientStrategy")
@AllArgsConstructor
public class SaveClientPhoto implements Strategy<ClientDto>{

    private final FlickrService flickrService;
    private final ClientService clientService;

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ClientDto client = clientService.findbyId(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregisterement de la photo de client", ErrorCodes.UPADTE_PHOTO_EXCEPTION);
        }
        client.setPhoto(urlPhoto);
        return clientService.save(client);
    }
}
