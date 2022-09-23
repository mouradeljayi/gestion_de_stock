package com.eljayi.gestiondestock.services.impl;

import com.eljayi.gestiondestock.dto.MouvementStockDto;
import com.eljayi.gestiondestock.exception.ErrorCodes;
import com.eljayi.gestiondestock.exception.InvalidEntityException;
import com.eljayi.gestiondestock.model.TypeMvtStock;
import com.eljayi.gestiondestock.repository.MouvementStockRepository;
import com.eljayi.gestiondestock.services.ArticleService;
import com.eljayi.gestiondestock.services.MouvementStockService;
import com.eljayi.gestiondestock.validator.MouvementStockValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class MouvementStockServiceIml implements MouvementStockService {

    private final MouvementStockRepository repository;
    private final ArticleService articleService;


    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null) {
            log.warn("Article ID is null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findbyId(idArticle);
        return repository.stockReelArticle(idArticle);
    }

    @Override
    public List<MouvementStockDto> mvtStkArticle(Integer idArticle) {
        return repository.findAllByArticleId(idArticle).stream()
                .map(MouvementStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MouvementStockDto entreeStock(MouvementStockDto dto) {
        return entreePositive(dto, TypeMvtStock.ENTREE);
    }

    @Override
    public MouvementStockDto sortieStock(MouvementStockDto dto) {
        return sortieNegative(dto, TypeMvtStock.SORTIE);
    }

    @Override
    public MouvementStockDto correctionStockPos(MouvementStockDto dto) {
        return entreePositive(dto, TypeMvtStock.CORRECTION_POS);
    }

    @Override
    public MouvementStockDto correctionStockNeg(MouvementStockDto dto) {
        return sortieNegative(dto, TypeMvtStock.CORRECTION_NEG);
    }

    private MouvementStockDto entreePositive(MouvementStockDto dto, TypeMvtStock type) {
        List<String> errors = MouvementStockValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Mouvement stock is not valid {}", dto);
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MOUVEMENT_STOCK_NOT_VALID, errors);
        }
        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));

        dto.setTypeMvtStock(type);

        return MouvementStockDto.fromEntity(
                repository.save(MouvementStockDto.toEntity(dto))
        );
    }

    private MouvementStockDto sortieNegative(MouvementStockDto dto, TypeMvtStock type) {
        List<String> errors = MouvementStockValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Mouvement stock is not valid {}", dto);
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MOUVEMENT_STOCK_NOT_VALID, errors);
        }
        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue()) * -1 ));

        dto.setTypeMvtStock(type);

        return MouvementStockDto.fromEntity(
                repository.save(MouvementStockDto.toEntity(dto))
        );
    }
}
