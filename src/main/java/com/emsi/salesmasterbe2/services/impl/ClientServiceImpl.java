package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.entities.Client;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.ClientRepository;
import com.emsi.salesmasterbe2.services.ClientService;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDao saveClient(ClientDao clientDao) {
        Client clientEntity = ObjectMapperUtils.map(clientDao, Client.class);
        clientEntity = clientRepository.save(clientEntity);
        return ObjectMapperUtils.map(clientEntity, ClientDao.class);
    }

    @Override
    public ClientDao getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new IllegalArgumentException("Client with ID " + id + " not found");
        }
        return ObjectMapperUtils.map(clientOptional.get(), ClientDao.class);
    }

    @Override
    public PagedResponse<ClientDao> getAllClients(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clientsPage = clientRepository.findAll(pageable);
        return new PagedResponse<>(
                ObjectMapperUtils.mapAll(clientsPage.getContent(), ClientDao.class),
                page,
                size,
                clientsPage.getNumberOfElements(),
                clientsPage.getTotalPages()
        );
    }

    @Override
    public ClientDao deleteClient(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
            return ObjectMapperUtils.map(clientOptional.get(), ClientDao.class);
        } else {
            throw new IllegalArgumentException("Client with ID " + id + " not found");
        }
    }
}
