package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.controller.api.MouvementStockApi;
import com.eljayi.gestiondestock.dto.MouvementStockDto;
import com.eljayi.gestiondestock.services.MouvementStockService;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MouvementStockController implements MouvementStockApi {

    private final MouvementStockService service;

    public MouvementStockController(MouvementStockService service) {
        this.service = service;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return service.stockReelArticle(idArticle);
    }

    @Override
    public List<MouvementStockDto> mvtStkArticle(Integer idArticle) {
        return service.mvtStkArticle(idArticle);
    }

    @Override
    public MouvementStockDto entreeStock(MouvementStockDto dto) {
        return service.entreeStock(dto);
    }

    @Override
    public MouvementStockDto sortieStock(MouvementStockDto dto) {
        return service.sortieStock(dto);
    }

    @Override
    public MouvementStockDto correctionStockPos(MouvementStockDto dto) {
        return service.correctionStockPos(dto);
    }

    @Override
    public MouvementStockDto correctionStockNeg(MouvementStockDto dto) {
        return service.correctionStockNeg(dto);
    }
}
