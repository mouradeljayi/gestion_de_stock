/* tslint:disable */
import { ArticleDto } from './article-dto';
export interface MouvementStockDto {
  article?: ArticleDto;
  dateMvt?: number;
  id?: number;
  idEntreprise?: number;
  quantite?: number;
  sourceMvt?: 'COMMANDE_CLIENT' | 'COMMANDE_FOURNISSEUR' | 'VENTE';
  typeMvtStock?: 'ENTREE' | 'SORTIE' | 'CORRECTION_POS' | 'CORRECTION_NEG';
}
