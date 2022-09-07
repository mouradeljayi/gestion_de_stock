package com.eljayi.gestiondestock.controller;


import com.eljayi.gestiondestock.controller.api.UtilisateurApi;
import com.eljayi.gestiondestock.dto.UtilisateurDto;
import com.eljayi.gestiondestock.services.UtilisateurService;

import java.util.List;

public class UtilisateurController implements UtilisateurApi {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findbyId(Integer id) {
        return utilisateurService.findbyId(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);

    }
}
