package com.eljayi.gestiondestock.services.impl;


import com.eljayi.gestiondestock.dto.EntrepriseDto;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.dto.UtilisateurDto;
import com.eljayi.gestiondestock.dto.RolesDto;
import com.eljayi.gestiondestock.repository.EntrepriseRepository;
import com.eljayi.gestiondestock.repository.RolesRepository;
import com.eljayi.gestiondestock.services.EntrepriseService;
import com.eljayi.gestiondestock.services.UtilisateurService;
import com.eljayi.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    private final RolesRepository rolesRepository;
    private final UtilisateurService utilisateurService;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, RolesRepository rolesRepository, UtilisateurService utilisateurService) {
        this.entrepriseRepository = entrepriseRepository;
        this.rolesRepository = rolesRepository;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Cette entreprise n'est pas valide {}", dto);
            throw new InvalidEntityException("Cette entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(dto))
        );

        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUser = utilisateurService.save(utilisateur);

        RolesDto rolesDto = RolesDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();
        rolesRepository.save(RolesDto.toEntity(rolesDto));

        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(dto)
                .dateDeNaissance(String.valueOf(Instant.now()))
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }

    @Override
    public EntrepriseDto findbyId(Integer id) {
        if(id == null)
        {
            log.error("ID de l'entreprise est null");
            return null;
        }
        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = " + id + " n'été trouvée dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("ID de l'entreprise est null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
