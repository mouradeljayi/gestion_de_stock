package com.eljayi.gestiondestock.validator;

import com.eljayi.gestiondestock.dto.VenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validate (VenteDto dto){
        List<String> errors = new ArrayList<>();

        if(dto == null)
        {
            errors.add("Veuillez renseigner le code du vente ");
            errors.add("Veuillez renseigner la date du vente ");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getCode()))
        {
            errors.add("Veuillez renseigner le code du vente ");
        }
        if (dto.getDateVente() == null)
        {
            errors.add("Veuillez renseigner la date du vente");
        }


        return errors;
    }
}
