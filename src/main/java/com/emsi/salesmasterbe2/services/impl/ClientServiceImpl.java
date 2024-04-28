package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.entities.Client;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.ClientRepository;
import com.emsi.salesmasterbe2.services.ClientService;
import com.emsi.salesmasterbe2.utils.ApiServiceUtils;
import com.emsi.salesmasterbe2.utils.AppUtils;
import com.emsi.salesmasterbe2.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private ApiServiceUtils apiServiceUtils;





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
        return apiServiceUtils.getAllEntities(page,size,clientRepository,ClientDao.class);
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
