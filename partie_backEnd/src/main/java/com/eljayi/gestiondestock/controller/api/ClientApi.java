package com.eljayi.gestiondestock.controller.api;


import com.eljayi.gestiondestock.dto.ClientDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "clients")
public interface ClientApi {
    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping( value = APP_ROOT + "/clients/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findbyId(@PathVariable("idClient") Integer id);

    @GetMapping( value = APP_ROOT + "/clients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    void delete( @PathVariable("idClient") Integer id);
}
