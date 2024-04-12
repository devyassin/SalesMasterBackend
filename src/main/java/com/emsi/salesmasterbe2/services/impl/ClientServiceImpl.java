package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.entities.Client;
import com.emsi.salesmasterbe2.repository.ClientRepository;
import com.emsi.salesmasterbe2.services.ClientService;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDao saveClient(ClientDao clientDao) {
        Client clientEntity =ObjectMapperUtils.map(clientDao,Client.class);
        return ObjectMapperUtils.map(clientEntity,ClientDao.class);
    }


    @Override
    public ClientDao getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        // Convertir l'entité Client en ClientDao
        return ObjectMapperUtils.map(clientOptional.get(),ClientDao.class);
    }

    @Override
    public List<ClientDao> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        // Convertir la liste d'entités Client en liste de ClientDao
        return ObjectMapperUtils.mapAll(clients,ClientDao.class);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
