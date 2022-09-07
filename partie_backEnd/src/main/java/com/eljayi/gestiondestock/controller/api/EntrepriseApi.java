package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;


public interface EntrepriseApi {
    @PostMapping(value = APP_ROOT + "/entreprises/create",consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping( value = APP_ROOT + "/entreprises/{idEntreprise}",produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findbyId(@PathVariable("idEntreprise") Integer id);

    @GetMapping( value = APP_ROOT + "/entreprises/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{idEntreprise}")
    void delete( @PathVariable("idEntreprise") Integer id);
}
