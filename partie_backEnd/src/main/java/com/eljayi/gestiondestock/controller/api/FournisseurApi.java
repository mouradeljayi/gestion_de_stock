package com.eljayi.gestiondestock.controller.api;


import com.eljayi.gestiondestock.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;


public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseurs/create",consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping( value = APP_ROOT + "/fournisseurs/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findbyId(@PathVariable("idFournisseur") Integer id);

    @GetMapping( value = APP_ROOT + "/fournisseurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
    void delete( @PathVariable("idFournisseur") Integer id);
}
