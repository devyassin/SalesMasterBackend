package com.emsi.salesmasterbe2.utils;

import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import com.emsi.salesmasterbe2.repository.ClientRepository;
import com.emsi.salesmasterbe2.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ApiServiceUtils {


    private ClientRepository clientRepository;
    private ProduitRepository produitRepository;

    public <T, U> PagedResponse<U> getAllEntitiesLogic( int page,
                                                        int size,
                                                        String name,
                                                        String type,
                                                        JpaRepository<T, Long> repository,
                                                        Class<U> dtoClass){
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<T> entitiesPage;

        if(!name.isEmpty() && type.equals("client") ){
            entitiesPage= (Page<T>) clientRepository.findByNomContains(name,pageable);
        }else if(!name.isEmpty() && type.equals("produits")){
            entitiesPage= (Page<T>) produitRepository.findByNomContains(name,pageable);
        }
        else {
            entitiesPage = repository.findAll(pageable);
        }

        long totalElementsInTable = repository.count();
        return new PagedResponse<>(
                ObjectMapperUtils.mapAll(entitiesPage.getContent(), dtoClass),
                page,
                size,
                entitiesPage.getNumberOfElements(),
                entitiesPage.getTotalPages(),
                totalElementsInTable);

    }
    public <T, U> PagedResponse<U> getAllEntities(
            int page,
            int size,
            JpaRepository<T, Long> repository,
            Class<U> dtoClass) {

        return getAllEntitiesLogic(page,size,"","",repository,dtoClass);
    }

    public <T, U, repository> PagedResponse<U> getAllEntities(
            int page,
            int size,
            String name,
            String type,
            JpaRepository<T, Long> repository,
            Class<U> dtoClass) {
        return getAllEntitiesLogic(page,size,name,type,repository,dtoClass);
    }
}
