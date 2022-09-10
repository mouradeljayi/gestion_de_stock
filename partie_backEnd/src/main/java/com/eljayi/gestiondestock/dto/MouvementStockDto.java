package com.eljayi.gestiondestock.dto;


import com.eljayi.gestiondestock.model.MouvementStock;
import com.eljayi.gestiondestock.model.TypeMvtStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MouvementStockDto {

    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStock typeMvtStock;

    private Integer  idEntreprise;

    public MouvementStockDto fromEntity (MouvementStock mouvementstock){
        if(mouvementstock == null){
            return null;
        }
        return MouvementStockDto.builder()
                .id(mouvementstock.getId())
                .quantite(mouvementstock.getQuantite())
                .dateMvt(mouvementstock.getDateMvt())
                .idEntreprise(mouvementstock.getIdEntreprise())
                .build();
    }
    public MouvementStock toEntity (MouvementStockDto mouvementstockDto){
        if(mouvementstockDto == null){
            return null;
        }
        MouvementStock mouvementstock = new MouvementStock();
        mouvementstock.setId(mouvementstockDto.getId());
        mouvementstock.setQuantite(mouvementstockDto.getQuantite());
        mouvementstock.setDateMvt(mouvementstock.getDateMvt());
        mouvementstock.setIdEntreprise(mouvementstockDto.getIdEntreprise());
        return mouvementstock;
    }
}
