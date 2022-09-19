package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.controller.api.CommandeFournisseurApi;
import com.eljayi.gestiondestock.dto.CommandeFournisseurDto;
import com.eljayi.gestiondestock.services.CommandeFournisseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    CommandeFournisseurService commandeFournisseurService;

    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto) {
        return ResponseEntity.ok(commandeFournisseurService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findbyId(Integer id) {
        return ResponseEntity.ok(commandeFournisseurService.findbyId(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
