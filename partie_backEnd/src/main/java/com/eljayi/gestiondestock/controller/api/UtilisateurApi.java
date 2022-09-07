package com.eljayi.gestiondestock.controller.api;


import com.eljayi.gestiondestock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;


public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT + "/utilisateurs/create",consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping( value = APP_ROOT + "/utilisateurs/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findbyId(@PathVariable("idUtilisateur") Integer id);

    @GetMapping( value = APP_ROOT + "/utilisateurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    void delete( @PathVariable("idUtilisateur") Integer id);
}
