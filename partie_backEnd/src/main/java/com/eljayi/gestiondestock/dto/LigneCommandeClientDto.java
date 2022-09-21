package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.LigneCommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer  idEntreprise;


    public static LigneCommandeClientDto fromEntity (LigneCommandeClient lignecommandeclient){
        if(lignecommandeclient == null){
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(lignecommandeclient.getId())
                .article(ArticleDto.fromEntity(lignecommandeclient.getArticle()))
                .quantite(lignecommandeclient.getQuantite())
                .prixUnitaire(lignecommandeclient.getPrixUnitaire())
                .idEntreprise(lignecommandeclient.getIdEntreprise())
                .build();
    }
    public static LigneCommandeClient toEntity (LigneCommandeClientDto lignecommandeclientDto){
        if(lignecommandeclientDto == null){
            return null;
        }
        LigneCommandeClient lignecommandeclient = new LigneCommandeClient();
        lignecommandeclient.setId(lignecommandeclientDto.getId());
        lignecommandeclient.setArticle(ArticleDto.toEntity(lignecommandeclientDto.getArticle()));
        lignecommandeclient.setQuantite(lignecommandeclientDto.getQuantite());
        lignecommandeclient.setPrixUnitaire(lignecommandeclientDto.getPrixUnitaire());
        lignecommandeclient.setIdEntreprise(lignecommandeclientDto.getIdEntreprise());
        return lignecommandeclient;
    }
}