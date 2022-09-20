package com.eljayi.gestiondestock.services.auth;

import com.eljayi.gestiondestock.dto.UtilisateurDto;
import com.eljayi.gestiondestock.model.auth.ExtendedUser;
import com.eljayi.gestiondestock.services.UtilisateurService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UtilisateurService service;

    public ApplicationUserDetailsService( UtilisateurService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateur = service.findByEmail(email);

        List<SimpleGrantedAuthority> authorities =  new ArrayList<>();
        utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new ExtendedUser(utilisateur.getEmail(), utilisateur.getMotDePasse(), utilisateur.getEntreprise().getId(), authorities);
    }
}
