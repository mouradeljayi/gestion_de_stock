package com.eljayi.gestiondestock.services;


import com.eljayi.gestiondestock.dto.CategorieDto;

import java.util.List;

public interface CategorieService {
    CategorieDto save(CategorieDto dto);

    CategorieDto findbyId(Integer id);

    CategorieDto findbyCode(String code);

    List<CategorieDto> findAll();

    void delete(Integer id);
}
