package com.eljayi.gestiondestock.services;


import com.eljayi.gestiondestock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findbyId(Integer id);

    List<EntrepriseDto> findAll();

    void delete(Integer id);
}
