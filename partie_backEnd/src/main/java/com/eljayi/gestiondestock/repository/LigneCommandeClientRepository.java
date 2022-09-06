package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
}
