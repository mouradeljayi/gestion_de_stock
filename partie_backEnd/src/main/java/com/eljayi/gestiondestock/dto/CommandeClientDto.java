package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.CommandeClient;
import com.eljayi.gestiondestock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    private Integer  idEntreprise;

    public static CommandeClientDto fromEntity (CommandeClient commandeclient){
        if(commandeclient == null){
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .dateCommande(commandeclient.getDateCommande())
                .etatCommande(commandeclient.getEtatCommande())
                .client(ClientDto.fromEntity(commandeclient.getClient()))
                .idEntreprise(commandeclient.getIdEntreprise())
                .build();
    }
    public static CommandeClient toEntity (CommandeClientDto commandeclientDto){
        if(commandeclientDto == null){
            return null;
        }
        CommandeClient commandeclient = new CommandeClient();
        commandeclient.setId(commandeclientDto.getId());
        commandeclient.setCode(commandeclientDto.getCode());
        commandeclient.setDateCommande(commandeclientDto.getDateCommande());
        commandeclient.setIdEntreprise(commandeclientDto.getIdEntreprise());
        commandeclient.setEtatCommande(commandeclientDto.getEtatCommande());
        //commandeclient.setClient(ClientDto.toEntity(commandeclientDto.getClient()));
        return commandeclient;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
