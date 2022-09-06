package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.CommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients;

    private ClientDto client;

    public CommandeClientDto fromEntity (CommandeClient commandeclient){
        if(commandeclient == null){
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .dateCommande(commandeclient.getDateCommande())
                .client(ClientDto.fromEntity(commandeclient.getClient()))
                .build();
    }
    public CommandeClient toEntity (CommandeClientDto commandeclientDto){
        if(commandeclientDto == null){
            return null;
        }
        CommandeClient commandeclient = new CommandeClient();
        commandeclient.setId(commandeclientDto.getId());
        commandeclient.setCode(commandeclientDto.getCode());
        commandeclient.setDateCommande(commandeclientDto.getDateCommande());
        commandeclient.setClient(ClientDto.toEntity(commandeclientDto.getClient()));
        return commandeclient;
    }
}
