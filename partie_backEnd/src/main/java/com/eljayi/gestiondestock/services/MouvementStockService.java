package com.eljayi.gestiondestock.services;

import com.eljayi.gestiondestock.dto.MouvementStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface MouvementStockService {

    BigDecimal stockReelArticle(Integer idArticle);

    List<MouvementStockDto> mvtStkArticle(Integer idArticle);

    MouvementStockDto entreeStock(MouvementStockDto dto);

    MouvementStockDto sortieStock(MouvementStockDto dto);

    MouvementStockDto correctionStockPos(MouvementStockDto dto);

    MouvementStockDto correctionStockNeg(MouvementStockDto dto);



}
