package com.eljayi.gestiondestock.services;

import com.eljayi.gestiondestock.dto.CommandeClientDto;
import com.eljayi.gestiondestock.dto.LigneCommandeClientDto;
import com.eljayi.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Integer idCommande, Integer idClient);

    CommandeClientDto findbyId(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void delete(Integer id);

    List<LigneCommandeClientDto> findAllLignesCommandesClientByClientId(Integer idCommande);
}
