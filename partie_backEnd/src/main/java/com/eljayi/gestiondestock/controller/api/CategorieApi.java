package com.eljayi.gestiondestock.controller.api;


import com.eljayi.gestiondestock.dto.CategorieDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

public interface CategorieApi {

    @PostMapping(value = APP_ROOT + "/categories/create",consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto save(@RequestBody CategorieDto dto);

    @GetMapping( value = APP_ROOT + "/categories/{idCategorie}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findbyId(@PathVariable("idCategorie") Integer id);

    @GetMapping( value = APP_ROOT + "/categories/{codeCategorie}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findbyCode(@PathVariable("codeCategorie") String code);

    @GetMapping( value = APP_ROOT + "/categories/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategorieDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategorie}")
    void delete( @PathVariable("idCategorie") Integer id);
}
