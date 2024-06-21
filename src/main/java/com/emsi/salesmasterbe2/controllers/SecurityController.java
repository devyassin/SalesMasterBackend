package com.emsi.salesmasterbe2.controllers;

import com.emsi.salesmasterbe2.daos.UtilisateurDao;
import com.emsi.salesmasterbe2.entities.Utilisateur;
import com.emsi.salesmasterbe2.payload.response.ProfileDetailsResponse;
import com.emsi.salesmasterbe2.payload.response.RegisterResponse;
import com.emsi.salesmasterbe2.repository.UtilisateurRepository;
import com.emsi.salesmasterbe2.services.impl.UtilisateurServiceImpl;
import com.emsi.salesmasterbe2.utils.GenerateFakeData;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {


    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UtilisateurServiceImpl utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private GenerateFakeData generateFakeData;


    @GetMapping("/profile")
    public ResponseEntity<ProfileDetailsResponse> authentication(Authentication authentication){

        String email=authentication.getName();
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email).get();
        ProfileDetailsResponse profileDetailsResponse=new ProfileDetailsResponse();
        profileDetailsResponse.setNom(utilisateur.getNom());
        profileDetailsResponse.setEmail(utilisateur.getEmail());
        profileDetailsResponse.setRole(utilisateur.getRole());
       return new ResponseEntity<>(profileDetailsResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String ,String>> login(@RequestBody UtilisateurDao utilisateurDao){

        Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(utilisateurDao.getEmail());
        if (!existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Bad Cridentials"));
        }

        if (!passwordEncoder.matches(utilisateurDao.getMotDePasse(), existingUser.get().getMotDePasse())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Bad Credentials"));
        }

        Authentication authentication= authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(utilisateurDao.getEmail(),
                                utilisateurDao.getMotDePasse()));

        Instant instant=Instant.now();
        String scope= authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.DAYS))
                .subject(existingUser.get().getEmail())
                .claim("scope",scope)
                .build();

        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.
                from(JwsHeader.with(MacAlgorithm.HS512).build(),jwtClaimsSet);
        String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        generateFakeData.generateFakeClients(95);
        generateFakeData.generateFakeProducts(40);
        generateFakeData.generateVentes();
        return ResponseEntity.ok(Map.of("access_token", jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UtilisateurDao utilisateurDao) {
        if (utilisateurRepository.findByEmail(utilisateurDao.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new RegisterResponse("Email is already in use", null));
        }else{
            utilisateurDao.setMotDePasse(passwordEncoder.encode(utilisateurDao.getMotDePasse()));
            utilisateurService.saveUtilisateur(utilisateurDao);

            RegisterResponse response = new RegisterResponse("User " +
                    "registered successfully", utilisateurDao);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }
}
