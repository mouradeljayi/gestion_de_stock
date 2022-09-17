package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.controller.api.FournisseurApi;
import com.eljayi.gestiondestock.services.FournisseurService;
import com.eljayi.gestiondestock.dto.FournisseurDto;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findbyId(Integer id) {
        return fournisseurService.findbyId(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);

    }
}
