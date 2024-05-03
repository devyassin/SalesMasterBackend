package com.emsi.salesmasterbe2;

import com.emsi.salesmasterbe2.entities.Role;
import com.emsi.salesmasterbe2.entities.Utilisateur;
import com.emsi.salesmasterbe2.repository.UtilisateurRepository;
import com.emsi.salesmasterbe2.services.impl.ClientServiceImpl;
import com.emsi.salesmasterbe2.utils.GenerateFakeData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class SalesMasterBe2Application {

    private GenerateFakeData generateFakeData;

    public static void main(String[] args) {
        SpringApplication.run(SalesMasterBe2Application.class, args);
    }

    @Bean
    CommandLineRunner start(UtilisateurRepository utilisateurRepositery){
        return  args -> {
//            Utilisateur utilisateur=new Utilisateur();
//            utilisateur.setNom("yassin lamouadden");
//            utilisateur.setEmail("mouden529@gmail.com");
//            utilisateur.setMotDePasse("1264qsfqsfqf");
//            utilisateur.setRole(Role.ADMIN);
//            utilisateurRepositery.save(utilisateur);
//            utilisateur=new Utilisateur();
//            utilisateur.setNom("Oussama Naaman ");
//            utilisateur.setEmail("Naaman@gmail.com");
//            utilisateur.setMotDePasse("123azerty.");
//            utilisateur.setRole(Role.ADMIN);
//            utilisateurRepositery.save(utilisateur);


            generateFakeData.generateFakeClients(95);

        };
    }

}
