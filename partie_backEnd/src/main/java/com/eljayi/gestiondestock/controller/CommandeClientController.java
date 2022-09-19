package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.controller.api.CommandeClientApi;
import com.eljayi.gestiondestock.dto.CommandeClientDto;
import com.eljayi.gestiondestock.services.CommandeClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private final CommandeClientService commandeClientService;

    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        //return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.save(dto));
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findbyId(Integer id) {
        return ResponseEntity.ok(commandeClientService.findbyId(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
