package com.eljayi.gestiondestock.services;


import com.eljayi.gestiondestock.dto.ClientDto;

import java.util.List;

public interface ClientService
{
    ClientDto save(ClientDto dto);
    ClientDto findbyId(Integer id);
    List<ClientDto> findAll();
    void delete(Integer id);
}
