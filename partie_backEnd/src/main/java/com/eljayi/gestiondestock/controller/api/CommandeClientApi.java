package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.CommandeClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(CommandeClientDto dto);

    @GetMapping( value = APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findbyId(@PathVariable Integer idCommandeClient);

    @GetMapping( value = APP_ROOT + "/commandesclients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping( value = APP_ROOT + "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping (value = APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
    ResponseEntity<?> delete(@PathVariable("idCommandeClient") Integer id);
}
