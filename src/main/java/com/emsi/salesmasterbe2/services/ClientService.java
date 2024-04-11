package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.ClientDao;

import java.util.List;

public interface ClientService {
    ClientDao saveClient(ClientDao clientDao);
    ClientDao getClientById(Long id);
    List<ClientDao> getAllClients();
    void deleteClient(Long id);
}
