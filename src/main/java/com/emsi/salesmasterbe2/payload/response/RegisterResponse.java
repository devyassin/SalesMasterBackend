package com.emsi.salesmasterbe2.payload.response;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String message;
    private UtilisateurDao user;
}
