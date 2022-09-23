package com.eljayi.gestiondestock.services.impl;


import com.eljayi.gestiondestock.dto.ClientDto;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.exception.InvalidOperationException;
import com.eljayi.gestiondestock.model.CommandeClient;
import com.eljayi.gestiondestock.repository.ClientRepository;
import com.eljayi.gestiondestock.repository.CommandeClientRepository;
import com.eljayi.gestiondestock.services.ClientService;
import com.eljayi.gestiondestock.validator.ClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CommandeClientRepository commandeClientRepository;

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Ce client n'est pas valide {}", dto);
            throw new InvalidEntityException("Ce client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));
    }

    @Override
    public ClientDto findbyId(Integer id) {
        if(id == null)
        {
            log.error("ID du client est null");
            return null;
        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec l'ID = " + id + " n'été trouvé dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("ID du client est null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
        if (!commandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un client qui a déja des commandes", ErrorCodes.CLIENT_ALREADY_IN_USE);
        }
        clientRepository.deleteById(id);

    }
}
