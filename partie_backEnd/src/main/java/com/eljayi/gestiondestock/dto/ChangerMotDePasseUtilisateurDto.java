package com.eljayi.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangerMotDePasseUtilisateurDto {

    private Integer id;

    private String motDePasse;

    private String confirmMotDePasse;
}
