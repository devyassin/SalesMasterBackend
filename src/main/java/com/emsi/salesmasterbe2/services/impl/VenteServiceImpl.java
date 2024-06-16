package com.emsi.salesmasterbe2.services.impl;

import com.emsi.salesmasterbe2.daos.*;
import com.emsi.salesmasterbe2.entities.Facture;
import com.emsi.salesmasterbe2.entities.LigneDeVente;
import com.emsi.salesmasterbe2.entities.Statut;
import com.emsi.salesmasterbe2.entities.Vente;
import com.emsi.salesmasterbe2.exception.ApiException;
import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.payload.response.VenteResponse;
import com.emsi.salesmasterbe2.payload.response.VenteResponseDetails;
import com.emsi.salesmasterbe2.payload.response.VenteResponseTable;
import com.emsi.salesmasterbe2.repository.LigneDeVenteRepository;
import com.emsi.salesmasterbe2.repository.VenteRepository;
import com.emsi.salesmasterbe2.services.FactureService;
import com.emsi.salesmasterbe2.services.LigneDeVenteService;
import com.emsi.salesmasterbe2.services.ProduitService;
import com.emsi.salesmasterbe2.services.VenteService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;
    private ApiServiceUtils apiServiceUtils;
    private LigneDeVenteServiceImpl ligneDeVenteService;
    private ProduitService produitService;
    private FactureService factureService;




    public void checkQuantiteForAllProducts(List<ProduitQauntiteDao> produitQauntiteDaos){
        produitQauntiteDaos.forEach(produitQauntiteDao -> {
            if(produitQauntiteDao.getQuantite()>produitQauntiteDao.getProduit().getQuantiteEnStock()){
                throw new ApiException(HttpStatus.BAD_REQUEST,"Quantité en stock pour"+
                        produitQauntiteDao.getProduit().getNom()+" est insuffisant !");
            }
        });
    }

    public void setQuantiteEnStockForProducts(List<ProduitQauntiteDao> produitQauntiteDaos){
        checkQuantiteForAllProducts(produitQauntiteDaos);
        produitQauntiteDaos.forEach(produitQauntiteDao -> {
            ProduitDao produit=produitQauntiteDao.getProduit();
            produit.setQuantiteEnStock(produit.getQuantiteEnStock()
                    -produitQauntiteDao.getQuantite());
            produitService.updateProduct(produit.getProduitId(),produit);
        });
    }

    public double calculTotalPriceForVente(List<ProduitQauntiteDao> produitQauntiteDaos){
        double totalPrice=0;
        for (int i=0;i<produitQauntiteDaos.size();i++){
            totalPrice+= produitQauntiteDaos.get(i).getQuantite()
                    *produitQauntiteDaos.get(i).getProduit().getPrix();
        }
        return totalPrice;
    }
    @Override
    public VenteDao saveVente(VenteDao venteDao) {
        setQuantiteEnStockForProducts(venteDao.getProduitQauntiteDao());
        venteDao.setTotal(calculTotalPriceForVente(venteDao.getProduitQauntiteDao()));
        venteDao.setDateVente(LocalDate.now());

        // Convert VenteDao to Vente entity
        Vente venteEntity = ObjectMapperUtils.map(venteDao, Vente.class);

        // Save LigneDeVentes and set them to the Vente entity
        List<LigneDeVente> ligneDeVentes = ligneDeVenteService.saveLigneDeVentes(venteEntity, venteDao);
        venteEntity.setLignesDeVentes(ligneDeVentes);

        // Save the Vente entity, which will cascade to save LigneDeVentes
        venteEntity = venteRepository.save(venteEntity);

        // Map the saved Vente entity back to VenteDao and return
        return ObjectMapperUtils.map(venteEntity, VenteDao.class);
    }

    @Override
    public VenteResponseDetails getVenteById(Long id) {
        Optional<Vente> venteOptional = venteRepository.findById(id);
        if (venteOptional.isEmpty()) {
            throw new IllegalArgumentException("Vente with ID " + id + " not found");
        }

        List<ProduitQauntiteDao> produitQauntiteDaos=new ArrayList<>();
        venteOptional.get().getLignesDeVentes().forEach(ligneDeVente -> {
        ProduitQauntiteDao produitQauntiteDao=new ProduitQauntiteDao();
                        produitQauntiteDao.setProduit(ObjectMapperUtils
                                                              .map(ligneDeVente.getProduit()
                                                                      ,ProduitDao.class));
                produitQauntiteDao.setQuantite(ligneDeVente.getQuantite());
                produitQauntiteDaos.add(produitQauntiteDao);
                    });
            VenteResponseDetails venteResponseDetails=
                    ObjectMapperUtils.map(venteOptional.get(), VenteResponseDetails.class);

            venteResponseDetails.setClient(
                    ObjectMapperUtils.map(venteOptional.get().getClient(), ClientDao.class));
            venteResponseDetails.setProduitQauntiteDaos(produitQauntiteDaos);
//            venteResponseDetails.setFacture(ObjectMapperUtils.map(venteOptional.get().
//                    getFacture(),FactureDao.class));
            return venteResponseDetails;
    }

    @Override
    public PagedResponse<VenteResponseTable> getAllVentes(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Vente> ventes;
        ventes=venteRepository.findAll(pageable);
        List<VenteResponseTable> venteResponseList=new ArrayList<>();
        ventes.getContent().forEach(vente->{

            VenteResponseTable venteResponse=new VenteResponseTable();
            venteResponse.setVenteId(vente.getVenteId());
            venteResponse.setDateVente(vente.getDateVente());
            venteResponse.setTotal(AppUtils.formatToTwoDecimalPlaces(vente.getTotal()));
            venteResponse.setStatut(vente.getStatut());
            venteResponse.setTotalProductTypes(vente.getLignesDeVentes().size());
            venteResponse.setClientName(vente.getClient().getNom());
            venteResponseList.add(venteResponse);
        });

        long totalElementsInTable = venteRepository.count();

        return new PagedResponse<>(
                venteResponseList,
                page,
                size,
                ventes.getNumberOfElements(),
                ventes.getTotalPages(),
                totalElementsInTable);
    }

    @Override
    public VenteDao updateVente(Long id, VenteDao venteDao) {
        Optional<Vente> existingVenteOptional = venteRepository.findById(id);
        if (existingVenteOptional.isPresent()) {
            Vente existingVente = existingVenteOptional.get();

            // Update fields of the existingVente from venteDao
            existingVente.setStatut(venteDao.getStatut());

            // Updating ProduitQauntiteDaos if needed
//            List<ProduitQauntiteDao> produitQauntiteDaos = venteDao.getProduitQauntiteDao();
//            if (produitQauntiteDaos != null && !produitQauntiteDaos.isEmpty()) {
//                setQuantiteEnStockForProducts(produitQauntiteDaos);
//                existingVente.getLignesDeVentes().clear();
//                List<LigneDeVente> updatedLignesDeVentes = ligneDeVenteService.saveLigneDeVentes(existingVente, venteDao);
//                existingVente.setLignesDeVentes(updatedLignesDeVentes);
//            }

            //Generation d'une facture
            FactureDao factureDao=new FactureDao();
            factureDao.setVente(ObjectMapperUtils.map(existingVente,VenteDao.class));
            factureDao.setDateFacturation(LocalDate.now());
            factureDao.setMontantTotal(existingVente.getTotal());
            factureDao.setPDF("url");
            factureService.saveFacture(factureDao);
//            existingVente.setFacture(ObjectMapperUtils.map(factureDao, Facture.class));
            Vente updatedVente = venteRepository.save(existingVente);

            return ObjectMapperUtils.map(updatedVente, VenteDao.class);
        } else {
            throw new IllegalArgumentException("Vente with ID " + id + " not found");
        }
    }

    @Override
    public VenteDao deleteVente(Long id) {
        Optional<Vente> venteOptional = venteRepository.findById(id);
        System.out.println(venteOptional.isPresent());
        System.out.println(id +"vente --------");
        if (venteOptional.isPresent()) {
            venteOptional.get().getLignesDeVentes().forEach(ligneDeVente -> {
                System.out.println(ligneDeVente.getLigneDeVenteId() + ":D");
            });
//            LigneDeVenteDao ligneDeVenteDao=ligneDeVenteService.deleteLigneDeVente
//                    (1L);
//            LigneDeVenteDao ligneDeVenteDao2=ligneDeVenteService.deleteLigneDeVente
//                    (2L);


//            venteRepository.deleteById(id);
            return ObjectMapperUtils.map(venteOptional.get(), VenteDao.class);
        } else {
            throw new IllegalArgumentException("Vente with ID " + id + " not found");
        }
    }
}


//List<VenteResponseTable> venteResponseList=new ArrayList<>();
//        ventes.getContent().forEach(vente->{
//List<ProduitQauntiteDao> produitQauntiteDaos=new ArrayList<>();
//VenteResponse venteResponse=new VenteResponse();
//            venteResponse.setDateVente(vente.getDateVente());
//        venteResponse.setTotal(vente.getTotal());
//        venteResponse.setStatut(vente.getStatut());
//        vente.getLignesDeVentes().forEach(ligneDeVente -> {
//ProduitQauntiteDao produitQauntiteDao=new ProduitQauntiteDao();
//                produitQauntiteDao.setProduit(ObjectMapperUtils
//                                                      .map(ligneDeVente.getProduit(),ProduitDao.class));
//        produitQauntiteDao.setQuantite(ligneDeVente.getQuantite());
//        produitQauntiteDaos.add(produitQauntiteDao);
//            });
//                    venteResponse.setProduitQauntiteDaos(produitQauntiteDaos);
//            venteResponse.setClientName(vente.getClient().getNom());
//        venteResponseList.add(venteResponse);
//        });