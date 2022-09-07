package com.eljayi.gestiondestock.services.impl;


import com.eljayi.gestiondestock.dto.EntrepriseDto;
import com.eljayi.gestiondestock.exception.EntityNotFoundException;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.model.Entreprise;
import com.eljayi.gestiondestock.repository.EntrepriseRepository;
import com.eljayi.gestiondestock.services.EntrepriseService;
import com.eljayi.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Cette entreprise n'est pas valide {}", dto);
            throw new InvalidEntityException("Cette entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(dto)));
    }

    @Override
    public EntrepriseDto findbyId(Integer id) {
        if(id == null)
        {
            log.error("ID de l'entreprise est null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec l'ID "+ id +" n'est trouvé dans la BDD", ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
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
