package com.emsi.salesmasterbe2.payload.response;

import com.emsi.salesmasterbe2.entities.Role;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDetailsResponse {
    private String nom;
    private String email;

    private Role role;
}
