package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.entities.Statut;
import com.emsi.salesmasterbe2.payload.response.CardDataStatsResponse;
import com.emsi.salesmasterbe2.payload.response.StockStatusCountResponse;
import com.emsi.salesmasterbe2.payload.response.VenteStatusCountResponse;
import com.emsi.salesmasterbe2.repository.*;
import com.emsi.salesmasterbe2.services.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DashboardServiceImpl  implements DashboardService {

    private final ProduitRepository produitRepository;
    private final VenteRepository venteRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final FactureRepository factureRepository;


    @Override
    public CardDataStatsResponse getCardDataStats() {
       long totalProducts= produitRepository.count();
       long totalVentes= venteRepository.count();
       long totalUsers= utilisateurRepository.count();
       long totalClients= clientRepository.count();
       long totalFactures= factureRepository.count();

       CardDataStatsResponse cardDataStatsResponse=new CardDataStatsResponse(totalClients,
               totalVentes,
               totalUsers,totalProducts,totalFactures);
        return cardDataStatsResponse;
    }

    @Override
    public VenteStatusCountResponse getVenteCountStats() {
        long nouvelle=venteRepository.countByStatut(Statut.NOUVELLE);
        long enCours=venteRepository.countByStatut(Statut.ENCOURS);
        long completed=venteRepository.countByStatut(Statut.COMPLETEE);
        VenteStatusCountResponse venteStatusCountResponse=new VenteStatusCountResponse(nouvelle,
                enCours,completed);
        return venteStatusCountResponse;
    }

    @Override
    public StockStatusCountResponse getStatusCountStats() {
        int[] counts = new int[3]; // counts[0] = faible, counts[1] = moyen, counts[2] = optimal

        produitRepository.findAll().forEach(produit -> {
            if (produit.getQuantiteEnStock() > 40) {
                counts[2]++;
            } else if (produit.getQuantiteEnStock() > 20 && produit.getQuantiteEnStock() <= 40) {
                counts[1]++;
            } else {
                counts[0]++;
            }
        });
        int faible = counts[0];
        int moyen = counts[1];
        int optimal = counts[2];
        return new StockStatusCountResponse(faible,moyen,optimal);
    }
}
