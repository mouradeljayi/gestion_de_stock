package com.eljayi.gestiondestock.controller;


import com.eljayi.gestiondestock.controller.api.UtilisateurApi;
import com.eljayi.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.eljayi.gestiondestock.dto.UtilisateurDto;
import com.eljayi.gestiondestock.services.UtilisateurService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    @Override
    public UtilisateurDto findByEmail(String email) {
        return utilisateurService.findByEmail(email);
    }

    @Override
    public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {
        return utilisateurService.changerMotDePasse(dto);
    }
}
