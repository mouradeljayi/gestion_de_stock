package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
    List<LigneVente> findAllByArticleId(Integer id);
    List<LigneVente> findAllByVenteId(Integer id);
}
