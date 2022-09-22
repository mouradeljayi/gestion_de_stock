package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
    List<LigneCommandeClient> findAllByCommandeClientId(Integer id);
    List<LigneCommandeClient> findAllByArticleId(Integer id);

}
