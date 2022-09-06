package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto article;

    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public LigneCommandeClientDto fromEntity (LigneCommandeClient lignecommandeclient){
        if(lignecommandeclient == null){
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(lignecommandeclient.getId())
                .quantite(lignecommandeclient.getQuantite())
                .prixUnitaire(lignecommandeclient.getPrixUnitaire())
                .build();
    }
    public LigneCommandeClient toEntity (LigneCommandeClientDto lignecommandeclientDto){
        if(lignecommandeclientDto == null){
            return null;
        }
        LigneCommandeClient lignecommandeclient = new LigneCommandeClient();
        lignecommandeclient.setId(lignecommandeclientDto.getId());
        lignecommandeclient.setQuantite(lignecommandeclientDto.getQuantite());
        lignecommandeclient.setPrixUnitaire(lignecommandeclientDto.getPrixUnitaire());
        return lignecommandeclient;
    }
}