package com.eljayi.gestiondestock.services;

import com.eljayi.gestiondestock.dto.CommandeFournisseurDto;
import com.eljayi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.eljayi.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto dto);

    CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

    // Delete Article == Delete LigneCommadeClient
    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeFournisseurDto findbyId(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();
    List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

    void delete(Integer id);
}
