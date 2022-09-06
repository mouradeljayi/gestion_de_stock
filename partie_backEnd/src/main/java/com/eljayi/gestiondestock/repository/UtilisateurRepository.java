package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
