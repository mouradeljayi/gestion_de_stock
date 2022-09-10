package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private VenteDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer  idEntreprise;

    public LigneVenteDto fromEntity (LigneVente lignevente){
        if(lignevente == null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(lignevente.getId())
                .quantite(lignevente.getQuantite())
                .prixUnitaire(lignevente.getPrixUnitaire())
                .idEntreprise(lignevente.getIdEntreprise())
                .build();
    }
    public LigneVente toEntity (LigneVenteDto ligneventeDto){
        if(ligneventeDto == null){
            return null;
        }
        LigneVente lignevente = new LigneVente();
        lignevente.setId(ligneventeDto.getId());
        lignevente.setQuantite(ligneventeDto.getQuantite());
        lignevente.setPrixUnitaire(lignevente.getPrixUnitaire());
        lignevente.setIdEntreprise(lignevente.getIdEntreprise());
        return lignevente;
    }
}
