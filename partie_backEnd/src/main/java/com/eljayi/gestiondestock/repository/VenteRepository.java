package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Integer> {
}
