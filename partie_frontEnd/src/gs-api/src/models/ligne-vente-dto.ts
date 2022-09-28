/* tslint:disable */
import { ArticleDto } from './article-dto';
import { VenteDto } from './vente-dto';
export interface LigneVenteDto {
  article?: ArticleDto;
  id?: number;
  idEntreprise?: number;
  prixUnitaire?: number;
  quantite?: number;
  vente?: VenteDto;
}
