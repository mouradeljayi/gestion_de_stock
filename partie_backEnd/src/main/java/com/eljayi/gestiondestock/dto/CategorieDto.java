package com.eljayi.gestiondestock.dto;

import com.eljayi.gestiondestock.model.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class CategorieDto {

    private Integer id;

    private String code;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> articles;

    private Integer  idEntreprise;


    public static CategorieDto fromEntity(Categorie categorie) {
        if (categorie == null) {
            return null;
        }
        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .idEntreprise(categorie.getIdEntreprise())
                .build();
    }
    public static Categorie toEntity(CategorieDto categorieDto){
        if(categorieDto == null){
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setCode(categorieDto.getCode());
        categorie.setDesignation(categorieDto.getDesignation());
        categorie.setIdEntreprise(categorieDto.getIdEntreprise());
        return categorie;
    }

}
