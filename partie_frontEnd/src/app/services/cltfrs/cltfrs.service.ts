import { Injectable } from '@angular/core';
import {UserService} from "../user/user.service";
import {ApiService} from "../../../gs-api/src/services/api.service";
import {ClientDto} from "../../../gs-api/src/models/client-dto";
import {iif, Observable, of} from "rxjs";
import {FournisseurDto} from "../../../gs-api/src/models/fournisseur-dto";

@Injectable({
  providedIn: 'root'
})
export class CltfrsService {

  constructor(
    private userService: UserService,
    private apiService: ApiService
  ) { }

  enregistrerClient(clientDto: ClientDto) : Observable<ClientDto> {
    clientDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id
    return this.apiService.save_2(clientDto);
  }

  enregistrerFournisseur(fournisseurDto: FournisseurDto) : Observable<FournisseurDto> {
    fournisseurDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id
    return this.apiService.save_6(fournisseurDto);
  }

  findAllClient(): Observable<ClientDto[]> {
    return this.apiService.findAll_2();
  }

  findAllFournisseurs(): Observable<FournisseurDto[]> {
    return this.apiService.findAll_6();
  }

  findClientById(idClient: number): Observable<ClientDto> {
    if (idClient) {
      return this.apiService.findbyId_2(idClient);
    }
    return of();
  }

  findFournisseurById(idFournisseur: number): Observable<FournisseurDto> {
    if (idFournisseur) {
      return this.apiService.findbyId_6(idFournisseur);
    }
    return of();
  }

  deleteClient(idClient: number): Observable<any> {
    if (idClient) {
      return this.apiService.delete_2(idClient);
    }
    return of();
  }

  deleteFournisseur(idFournisseur: number): Observable<any> {
    if (idFournisseur) {
      return this.apiService.delete_6(idFournisseur);
    }
    return of();
  }
}
