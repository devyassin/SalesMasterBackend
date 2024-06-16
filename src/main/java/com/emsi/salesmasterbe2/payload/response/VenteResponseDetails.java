package com.emsi.salesmasterbe2.payload.response;

import com.emsi.salesmasterbe2.daos.ClientDao;
import com.emsi.salesmasterbe2.daos.FactureDao;
import com.emsi.salesmasterbe2.daos.ProduitQauntiteDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class VenteResponseDetails extends VenteResponse{
    private ClientDao client;
//    private FactureDao facture;
    private List<ProduitQauntiteDao> produitQauntiteDaos;
}
