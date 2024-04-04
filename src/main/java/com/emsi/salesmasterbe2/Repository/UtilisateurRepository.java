package com.emsi.salesmasterbe2.Repository;


import com.emsi.salesmasterbe2.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
