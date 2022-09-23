package com.eljayi.gestiondestock.dto;


import com.eljayi.gestiondestock.model.MouvementStock;
import com.eljayi.gestiondestock.model.SourceMvtStock;
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

    private SourceMvtStock sourceMvt;

    private Integer idEntreprise;

    public static MouvementStockDto fromEntity(MouvementStock mouvementstock){
        if(mouvementstock == null){
            return null;
        }
        return MouvementStockDto.builder()
                .id(mouvementstock.getId())
                .quantite(mouvementstock.getQuantite())
                .dateMvt(mouvementstock.getDateMvt())
                .article(ArticleDto.fromEntity(mouvementstock.getArticle()))
                .typeMvtStock(mouvementstock.getTypeMvtStock())
                .sourceMvt(mouvementstock.getSourceMvt())
                .idEntreprise(mouvementstock.getIdEntreprise())
                .build();
    }
    public static MouvementStock toEntity (MouvementStockDto mouvementstockDto){
        if(mouvementstockDto == null){
            return null;
        }
        MouvementStock mouvementstock = new MouvementStock();
        mouvementstock.setId(mouvementstockDto.getId());
        mouvementstock.setQuantite(mouvementstockDto.getQuantite());
        mouvementstock.setDateMvt(mouvementstockDto.getDateMvt());
        mouvementstock.setArticle(ArticleDto.toEntity(mouvementstockDto.getArticle()));
        mouvementstock.setTypeMvtStock(mouvementstockDto.getTypeMvtStock());
        mouvementstock.setIdEntreprise(mouvementstockDto.getIdEntreprise());
        mouvementstock.setSourceMvt(mouvementstockDto.getSourceMvt());
        return mouvementstock;
    }
}
