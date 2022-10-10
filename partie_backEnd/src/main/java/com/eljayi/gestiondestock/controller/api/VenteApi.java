package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.VenteDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "ventes")
public interface VenteApi {

    @PostMapping(value = APP_ROOT + "/ventes/create")
    VenteDto save(@RequestBody VenteDto dto);

    @GetMapping( value = APP_ROOT + "/ventes/{idVente}")
    VenteDto findbyId(@PathVariable("idVente") Integer id);

    @GetMapping( value = APP_ROOT + "/ventes/{codeVente}")
    VenteDto findbyCode(@PathVariable("codeVente") String code);

    @GetMapping( value = APP_ROOT + "/ventes/all")
    List<VenteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{idVente}")
    void delete( @PathVariable("idVente") Integer id);
}
