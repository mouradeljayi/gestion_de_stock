package com.eljayi.gestiondestock.controller.api;


import com.eljayi.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.eljayi.gestiondestock.dto.UtilisateurDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "utilisateurs")
public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT + "/utilisateurs/create",consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping( value = APP_ROOT + "/utilisateurs/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findbyId(@PathVariable("idUtilisateur") Integer id);

    @GetMapping( value = APP_ROOT + "/utilisateurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    void delete( @PathVariable("idUtilisateur") Integer id);
    @GetMapping( value = APP_ROOT + "/utilisateurs/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByEmail(@PathVariable("email") String email);

    @PostMapping(value = APP_ROOT + "/utilisateurs/changepassword",consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto);

}
