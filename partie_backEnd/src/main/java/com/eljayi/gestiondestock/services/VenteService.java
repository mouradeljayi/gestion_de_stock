package com.eljayi.gestiondestock.services;

import com.eljayi.gestiondestock.dto.VenteDto;

import java.util.List;

public interface VenteService {

    VenteDto save(VenteDto dto);

    VenteDto findbyId(Integer id);

    VenteDto findByCode(String code);

    List<VenteDto> findAll();

    void delete(Integer id);
}
