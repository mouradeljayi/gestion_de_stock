package com.eljayi.gestiondestock.validator;

import com.eljayi.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner une description de l'entreprise");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            errors.add("Veuillez renseigner le code fiscal de l'entreprise");
            errors.add("Veuillez renseigner l'email de l'entreprise");
            errors.add("Veuillez renseigner le numéro de telephone de l'entreprise");

            return errors;
        }
        if(!StringUtils.hasLength(dto.getNom()))
        {
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getDescription()))
        {
            errors.add("Veuillez renseigner une description de l'entreprise");
        }
        if(dto.getAdresse() == null ){
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
        }
        else {
            if (!StringUtils.hasLength(dto.getAdresse().getAdresse1())) {
                errors.add("Le champs 'Adresse 1' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getVille())) {
                errors.add("Le champs 'Ville' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getCodePostal())) {
                errors.add("Le champs 'Code Postale' est obligatoire");
            }
            if (!StringUtils.hasLength(dto.getAdresse().getPays())) {
                errors.add("Le champs 'Pays' est obligatoire");
            }

        }
        if(!StringUtils.hasLength(dto.getCodeFiscal()))
        {
            errors.add("Veuillez renseigner le code fiscal de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getEmail()))
        {
            errors.add("Veuillez renseigner l'email de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getNumTel()))
        {
            errors.add("Veuillez renseigner le numéro de telephone de l'entreprise");
        }

        return errors;
    }
}
