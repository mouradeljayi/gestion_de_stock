package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.CommandeClientDto;
import com.eljayi.gestiondestock.dto.LigneCommandeClientDto;
import com.eljayi.gestiondestock.model.EtatCommande;
import com.eljayi.gestiondestock.model.LigneCommandeClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(CommandeClientDto dto);
    @PatchMapping(value = APP_ROOT + "/commandesclients/etat/update/{idCommande}/{etatCommande}")
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);
    @PatchMapping(value = APP_ROOT + "/commandesclients/quantite/update/{idCommande}/{idLigneCommande}/{quantite}")
    ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);
    @PatchMapping(value = APP_ROOT + "/commandesclients/client/update/{idCommande}/{idClient}")
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);
    @PatchMapping(value = APP_ROOT + "/commandesclients/article/update/{idCommande}/{idLigneCommande}/{idArticle}")
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = APP_ROOT + "/commandesclients/article/delete/{idCommande}/{idLigneCommande}")
    ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping( value = APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findbyId(@PathVariable Integer idCommandeClient);

    @GetMapping( value = APP_ROOT + "/commandesclients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping( value = APP_ROOT + "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();
    @GetMapping( value = APP_ROOT + "/commandesclients/lignescommande/{idCommande}")
    ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);

    @DeleteMapping (value = APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
    ResponseEntity<?> delete(@PathVariable("idCommandeClient") Integer id);
}
