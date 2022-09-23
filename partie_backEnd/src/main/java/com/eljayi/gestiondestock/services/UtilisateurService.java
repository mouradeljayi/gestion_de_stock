package com.eljayi.gestiondestock.services;

import com.eljayi.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.eljayi.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findbyId(Integer id);

    List<UtilisateurDto> findAll();

    void delete(Integer id);

    UtilisateurDto findByEmail(String email);
    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);
}
