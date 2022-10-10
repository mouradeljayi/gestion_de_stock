package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.MouvementStockDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "mouvementstock")
public interface MouvementStockApi {

    @GetMapping(APP_ROOT + "/mouvementstock/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(APP_ROOT + "/mouvementstock/articles/{idArticle}")
    List<MouvementStockDto> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

    @PostMapping(APP_ROOT + "/mouvementstock/entree")
    MouvementStockDto entreeStock(@RequestBody MouvementStockDto dto);

    @PostMapping(APP_ROOT + "/mouvementstock/sortie")
    MouvementStockDto sortieStock(@RequestBody MouvementStockDto dto);

    @PostMapping(APP_ROOT + "/mouvementstock/correctionpos")
    MouvementStockDto correctionStockPos(@RequestBody MouvementStockDto dto);

    @PostMapping(APP_ROOT + "/mouvementstock/correctionneg")
    MouvementStockDto correctionStockNeg(@RequestBody MouvementStockDto dto);
}
