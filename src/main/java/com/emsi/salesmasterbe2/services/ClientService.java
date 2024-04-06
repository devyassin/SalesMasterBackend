package com.emsi.salesmasterbe2.services;

import com.emsi.salesmasterbe2.entities.Client;
import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client getClientById(Long id);

    Client saveClient(Client client);

    void deleteClient(Long id);
}
