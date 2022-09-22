package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.CommandeFournisseur;
import com.eljayi.gestiondestock.model.EtatCommande;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;
    private EtatCommande etatCommande;

    @JsonIgnore
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    private FournisseurDto fournisseur;

    private Integer  idEntreprise;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){
        if(commandeFournisseur == null){
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .build();
    }
    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto){
        if(commandeFournisseurDto == null){
            return null;
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());
        commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
        commandeFournisseur.setEtatCommande(commandeFournisseurDto.getEtatCommande());
        commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
        commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));
        return commandeFournisseur;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }


}
