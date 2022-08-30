package com.eljayi.gestiondestock.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categorie")
public class Categorie extends AbstractEntity {

    @Column (name = "code")
    private String code;

    @Column (name = "designation")
    private String designation;

    @Column(name ="identreprise")
    private Integer  idEntreprise;

    @OneToMany (mappedBy = "categorie")
    private List<Article> articles;


}
