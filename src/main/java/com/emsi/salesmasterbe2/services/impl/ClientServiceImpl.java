package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.entities.Client;
import com.emsi.salesmasterbe2.exception.ApiException;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private ApiServiceUtils apiServiceUtils;


    @Override
    public ClientDao saveClient(ClientDao clientDao) {
        if(clientRepository.existsClientByEmail(clientDao.getEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Email already exists");
        }

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
    public PagedResponse<ClientDao> getAllClients(int page, int size,String nom) {
        return apiServiceUtils.getAllEntities(page,size,nom,"client",clientRepository,ClientDao.class);
    }

    @Override
    public ClientDao updateClient(Long id, ClientDao clientDao) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client existingClient = clientOptional.get();
            Client newModifiedClient= ObjectMapperUtils.map(clientDao, existingClient);

            Client updatedClient = clientRepository.save(newModifiedClient);

            return ObjectMapperUtils.map(updatedClient, ClientDao.class);
        } else {
            throw new IllegalArgumentException("Client with ID " + id + " not found");
        }
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
