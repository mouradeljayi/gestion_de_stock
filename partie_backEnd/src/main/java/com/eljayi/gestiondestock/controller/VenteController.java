package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.controller.api.VenteApi;
import com.eljayi.gestiondestock.dto.VenteDto;
import com.eljayi.gestiondestock.services.VenteService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteApi {

    private final VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        return venteService.save(dto);
    }

    @Override
    public VenteDto findbyId(Integer id) {
        return venteService.findbyId(id);
    }

    @Override
    public VenteDto findbyCode(String code) {
        return venteService.findByCode(code);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
