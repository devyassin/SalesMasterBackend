package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.payload.response.CardDataStatsResponse;
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
}
