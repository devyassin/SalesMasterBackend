package com.emsi.salesmasterbe2.daos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitQauntiteDao {
    private ProduitDao produit;
    private int quantite;
}
