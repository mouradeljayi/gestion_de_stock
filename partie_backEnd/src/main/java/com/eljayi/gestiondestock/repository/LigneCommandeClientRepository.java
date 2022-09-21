package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
    Optional<LigneCommandeClient> findAllByCommandeClientId(Integer idCommande);
}
