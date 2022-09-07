package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.controller.api.CategorieApi;
import com.eljayi.gestiondestock.dto.CategorieDto;
import com.eljayi.gestiondestock.services.CategorieService;

import java.util.List;

public class CategorieController implements CategorieApi {
    private final CategorieService categorieService;

    public CategorieController( CategorieService categorieService ) {
        this.categorieService = categorieService;
    }

    @Override
    public CategorieDto save(CategorieDto dto) {
        return categorieService.save(dto);
    }

    @Override
    public CategorieDto findbyId(Integer id) {
        return categorieService.findbyId(id);
    }

    @Override
    public CategorieDto findbyCode(String code) {
        return categorieService.findbyCode(code);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categorieService.delete(id);

    }

}
