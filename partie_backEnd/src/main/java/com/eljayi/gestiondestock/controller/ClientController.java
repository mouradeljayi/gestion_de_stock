package com.eljayi.gestiondestock.controller;


import com.eljayi.gestiondestock.controller.api.ClientApi;
import com.eljayi.gestiondestock.dto.ClientDto;
import com.eljayi.gestiondestock.services.ClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findbyId(Integer id) {
        return clientService.findbyId(id);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);

    }
}
