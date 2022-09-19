package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "/commandesfournisseurs/create")
    ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto);

    @GetMapping( value = APP_ROOT + "/commandesfournisseurs/{idCommandeFournisseur}")
    ResponseEntity<CommandeFournisseurDto> findbyId(@PathVariable Integer idCommandeFournisseur);

    @GetMapping( value = APP_ROOT + "/commandesfournisseurs/{codeCommandeFournisseur}")
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping( value = APP_ROOT + "/commandesfournisseurs/all")
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @DeleteMapping (value = APP_ROOT + "/commandesfournisseurs/delete/{idCommandeFournisseur}")
    ResponseEntity<?> delete(@PathVariable("idCommandeFournisseur") Integer id);
}
