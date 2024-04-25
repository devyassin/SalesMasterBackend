package com.emsi.salesmasterbe2.utils;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.services.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;


@AllArgsConstructor
@Service
public class GenerateFakeData {

    private  ClientServiceImpl clientService;
    public  void generateFakeClients(int numClients){
        Faker faker = new Faker(Locale.FRANCE);

        for (int i = 0; i < numClients; i++) {
            ClientDao clientDao = new ClientDao();
            clientDao.setNom(faker.name().fullName());
            clientDao.setAdresse(faker.address().fullAddress());
            clientDao.setEmail(faker.internet().emailAddress());
            clientDao.setTelephone(faker.phoneNumber().phoneNumber());
            clientService.saveClient(clientDao);
        }
    }
}
