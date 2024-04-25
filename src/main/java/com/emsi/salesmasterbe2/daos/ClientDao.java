package com.emsi.salesmasterbe2.daos;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ClientDao {
    private Long clientId;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;

}
