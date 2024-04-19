package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.services.ClientService;
import com.emsi.salesmasterbe2.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;



    @PostMapping
    public ResponseEntity<ClientDao> createClient(@RequestBody ClientDao clientDao) {
        ClientDao createdClient = clientService.saveClient(clientDao);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDao> getClientById(@PathVariable Long id) {
        ClientDao clientDao = clientService.getClientById(id);
        if (clientDao != null) {
            return new ResponseEntity<>(clientDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ClientDao>> getAllClients(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                                  @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<ClientDao> clients = clientService.getAllClients(page, size);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDao> deleteClient(@PathVariable Long id) {
        ClientDao clientDao = clientService.deleteClient(id);
        if (clientDao != null) {
            return new ResponseEntity<>(clientDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
