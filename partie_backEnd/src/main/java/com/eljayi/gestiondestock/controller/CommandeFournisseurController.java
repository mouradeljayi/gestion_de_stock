package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.controller.api.CommandeFournisseurApi;
import com.eljayi.gestiondestock.dto.CommandeFournisseurDto;
import com.eljayi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.eljayi.gestiondestock.model.EtatCommande;
import com.eljayi.gestiondestock.services.CommandeFournisseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    CommandeFournisseurService commandeFournisseurService;

    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto) {
        return ResponseEntity.ok(commandeFournisseurService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeFournisseurService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.updateFournisseur(idCommande, idFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeFournisseurService.deleteArticle(idCommande, idLigneCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findbyId(Integer id) {
        return ResponseEntity.ok(commandeFournisseurService.findbyId(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
        return ResponseEntity.ok(commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
