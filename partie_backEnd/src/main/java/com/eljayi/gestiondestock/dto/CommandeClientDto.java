package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

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
        //commandeclient.setClient(ClientDto.toEntity(commandeclientDto.getClient()));
        return commandeclient;
    }
}
