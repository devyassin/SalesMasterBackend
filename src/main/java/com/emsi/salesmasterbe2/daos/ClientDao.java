package com.emsi.salesmasterbe2.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ClientDao {
    private Long clientId;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;

}
