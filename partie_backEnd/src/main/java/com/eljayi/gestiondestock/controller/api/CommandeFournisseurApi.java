package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.CommandeFournisseurDto;
import com.eljayi.gestiondestock.dto.LigneCommandeClientDto;
import com.eljayi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.eljayi.gestiondestock.model.EtatCommande;
import com.eljayi.gestiondestock.model.LigneCommandeFournisseur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "/commandesfournisseurs/create")
    ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto);
    @PatchMapping(value = APP_ROOT + "/commandesfournisseurs/etat/update/{idCommande}/{etatCommande}")
    ResponseEntity<CommandeFournisseurDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);
    @PatchMapping(value = APP_ROOT + "/commandesfournisseurs/quantite/update/{idCommande}/{idLigneCommande}/{quantite}")
    ResponseEntity<CommandeFournisseurDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);
    @PatchMapping(value = APP_ROOT + "/commandesfournisseurs/client/update/{idCommande}/{idFournisseur}")
    ResponseEntity<CommandeFournisseurDto> updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);
    @PatchMapping(value = APP_ROOT + "/commandesfournisseurs/article/update/{idCommande}/{idLigneCommande}/{idArticle}")
    ResponseEntity<CommandeFournisseurDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = APP_ROOT + "/commandesfournisseurs/article/delete/{idCommande}/{idLigneCommande}")
    ResponseEntity<CommandeFournisseurDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping( value = APP_ROOT + "/commandesfournisseurs/{idCommandeFournisseur}")
    ResponseEntity<CommandeFournisseurDto> findbyId(@PathVariable Integer idCommandeFournisseur);

    @GetMapping( value = APP_ROOT + "/commandesfournisseurs/{codeCommandeFournisseur}")
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping( value = APP_ROOT + "/commandesfournisseurs/all")
    ResponseEntity<List<CommandeFournisseurDto>> findAll();
    @GetMapping( value = APP_ROOT + "/commandesfournisseurs/lignescommande/{idCommande}")
    ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLignesCommandesFournisseurByCommandeFournisseurId(@PathVariable("idCommande") Integer idCommande);

    @DeleteMapping (value = APP_ROOT + "/commandesfournisseurs/delete/{idCommandeFournisseur}")
    ResponseEntity<?> delete(@PathVariable("idCommandeFournisseur") Integer id);
}
