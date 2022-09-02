package com.eljayi.gestiondestock.validator;

import com.eljayi.gestiondestock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null)
        {
            errors.add("Veuillez renseigner le nom du client");
            errors.add("Veuillez renseigner le prénom du client ");
            errors.add("Veuillez renseigner le mail du client");
            errors.add("Veuillez renseigner le numéro de telephone du client");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getNom()))
        {
            errors.add("Veuillez renseigner le nom du client");
        }
        if(!StringUtils.hasLength(dto.getPrenom()))
        {
            errors.add("Veuillez renseigner le prénom du client");
        }
        if(!StringUtils.hasLength(dto.getMail()))
        {
            errors.add("Veuillez renseigner le mail du client");
        }
        if(!StringUtils.hasLength(dto.getNumTel()))
        {
            errors.add("Veuillez renseigner le numéro de telephone du client");
        }
        return errors;
    }
}
