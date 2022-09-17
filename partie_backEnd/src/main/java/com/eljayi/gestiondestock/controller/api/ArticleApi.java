package com.eljayi.gestiondestock.controller.api;

import com.eljayi.gestiondestock.dto.ArticleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;


public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier un article", description = "Cette méthode permet d'ajouter ou modifier un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet article crée / modifie"),
            @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @Operation(summary = "Rechercher un article par ID", description = "Cette méthode permet de rechercher un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec l'ID fournit")
    })
    @GetMapping( value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findbyId(@PathVariable("idArticle") Integer id);

    @Operation(summary = "Rechercher un article par CODE", description = "Cette méthode permet de rechercher un article par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec le code fournit")
    })
    @GetMapping( value = APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findbyCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @Operation(summary = "Renvoi la liste des articles", description = "Cette méthode permet de renvoyer la liste des articles qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La listes des articles / Une liste vide"),
    })
    @GetMapping( value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @Operation(summary = "Supprimer un article", description = "Cette méthode permet de supprimer un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été supprimé"),
    })
    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    void delete( @PathVariable("idArticle") Integer id);
}
