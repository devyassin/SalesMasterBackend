package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;

public interface ClientService {
    ClientDao saveClient(ClientDao clientDao);
    ClientDao getClientById(Long id);
    PagedResponse<ClientDao> getAllClients(int page, int size);
    ClientDao deleteClient(Long id);
}
