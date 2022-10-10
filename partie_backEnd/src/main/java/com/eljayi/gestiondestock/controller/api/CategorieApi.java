package com.eljayi.gestiondestock.controller.api;


import com.eljayi.gestiondestock.dto.CategorieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "categories")
public interface CategorieApi {

    @Operation(summary = "Enregistrer ou modifier une categorie", description = "Cette méthode permet d'ajouter ou modifier une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet categorie crée / modifie"),
            @ApiResponse(responseCode = "400", description = "L'objet categorie n'est pas valide")
    })
    @PostMapping(value = APP_ROOT + "/categories/create",consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto save(@RequestBody CategorieDto dto);

    @Operation(summary = "Rechercher une categorie par ID", description = "Cette méthode permet de rechercher une categorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La categorie a été trouvée dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie n'existe dans la BDD avec l'ID fournit")
    })
    @GetMapping( value = APP_ROOT + "/categories/{idCategorie}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findbyId(@PathVariable("idCategorie") Integer id);

    @Operation(summary = "Rechercher une categorie par CODE", description = "Cette méthode permet de rechercher une catégorie par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été trouvée dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie n'existe dans la BDD avec le CODE fournit")
    })
    @GetMapping( value = APP_ROOT + "/categories/{codeCategorie}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findbyCode(@PathVariable("codeCategorie") String code);

    @Operation(summary = "Renvoi la liste des catégories", description = "Cette méthode permet de renvoyer la liste des catégories qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La listes des catégories / Une liste vide"),
    })
    @GetMapping( value = APP_ROOT + "/categories/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategorieDto> findAll();

    @Operation(summary = "Supprimer une catégorie", description = "Cette méthode permet de supprimer une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été supprimé"),
    })
    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategorie}")
    void delete( @PathVariable("idCategorie") Integer id);
}
