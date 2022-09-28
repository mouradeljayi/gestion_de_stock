/* tslint:disable */
import { UtilisateurDto } from './utilisateur-dto';
export interface RolesDto {
  id?: number;
  idEntreprise?: number;
  roleName?: string;
  utilisateur?: UtilisateurDto;
}
