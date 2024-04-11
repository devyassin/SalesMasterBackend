package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.entities.Client;
import com.emsi.salesmasterbe2.repository.ClientRepository;
import com.emsi.salesmasterbe2.services.ClientService;
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
        Client clientEntity = convertToClientEntity(clientDao);
        return convertToClientDao(clientRepository.save(clientEntity));
    }


    @Override
    public ClientDao getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        // Convertir l'entité Client en ClientDao
        return clientOptional.map(this::convertToClientDao).orElse(null);
    }

    @Override
    public List<ClientDao> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        // Convertir la liste d'entités Client en liste de ClientDao
        return clients.stream().map(this::convertToClientDao).collect(Collectors.toList());
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    private ClientDao convertToClientDao(Client client) {
        ClientDao clientDao = new ClientDao();
        clientDao.setClientId(client.getClientID());
        clientDao.setNom(client.getNom());
        clientDao.setAdresse(client.getDescription());
        clientDao.setEmail(client.getEmail());
        clientDao.setTelephone(client.getTéléphone());
        return clientDao;
    }

    private Client convertToClientEntity(ClientDao clientDao) {
        Client clientEntity = new Client();
        clientEntity.setClientID(clientDao.getClientId());
        clientEntity.setNom(clientDao.getNom());
        clientEntity.setDescription(clientDao.getAdresse());
        clientEntity.setEmail(clientDao.getEmail());
        clientEntity.setTéléphone(clientDao.getTelephone());
        return clientEntity;
    }

}
