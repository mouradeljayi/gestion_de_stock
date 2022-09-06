package com.eljayi.gestiondestock.repository;

import com.eljayi.gestiondestock.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {
}
