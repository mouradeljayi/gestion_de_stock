package com.eljayi.gestiondestock.validator;

import com.eljayi.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {
    public static List<String> validate (CommandeFournisseurDto dto){
        List<String> errors = new ArrayList<>();

        if(dto == null)
        {
            errors.add("Veuillez renseigner le code de la commande ");
            errors.add("Veuillez renseigner la date de la commande ");
            errors.add("Veuillez selectionner le fournisseur de la commande ");
            errors.add("Veuillez selectionner l'état de la commande ");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getCode()))
        {
            errors.add("Veuillez renseigner le code de le commande ");
        }
        if (dto.getFournisseur() == null || dto.getFournisseur().getId() == null)
        {
            errors.add("Veuillez selectionner le fournisseur de la commande ");
        }
        if (!StringUtils.hasLength(dto.getEtatCommande().toString()))
        {
            errors.add("Veuillez selectionner l'état de la commande ");
        }

        return errors;
    }
}
