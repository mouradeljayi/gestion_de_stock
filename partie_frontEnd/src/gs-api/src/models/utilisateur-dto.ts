/* tslint:disable */
import { AdresseDto } from './adresse-dto';
import { EntrepriseDto } from './entreprise-dto';
import { RolesDto } from './roles-dto';
export interface UtilisateurDto {
  adresse?: AdresseDto;
  dateDeNaissance?: string;
  email?: string;
  entreprise?: EntrepriseDto;
  id?: number;
  motDePasse?: string;
  nom?: string;
  photo?: string;
  prenom?: string;
  roles?: Array<RolesDto>;
}
