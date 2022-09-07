package com.eljayi.gestiondestock.services;


import com.eljayi.gestiondestock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findbyId(Integer id);

    List<FournisseurDto> findAll();

    void delete(Integer id);
}
