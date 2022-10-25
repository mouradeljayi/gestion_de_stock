import {Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ClientDto} from "../../../gs-api/src/models/client-dto";
import {AdresseDto} from "../../../gs-api/src/models/adresse-dto";
import {CltfrsService} from "../../services/cltfrs/cltfrs.service";
import {FournisseurDto} from "../../../gs-api/src/models/fournisseur-dto";

@Component({
  selector: 'app-nouvel-clt-frs',
  templateUrl: './nouvel-clt-frs.component.html',
  styleUrls: ['./nouvel-clt-frs.component.css']
})
export class NouvelCltFrsComponent implements OnInit {

  origin = ''

  clientFournisseur: any = {}
  adresse: AdresseDto = {}
  errorMsg: Array<string> = [];


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private cltfrsService: CltfrsService
  ) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data['origin']
    })
  }

  enregistrer() {
    if (this.origin == 'client') {
      this.cltfrsService.enregistrerClient(this.mapToClient())
        .subscribe(client => {
          this.router.navigate(['clients'])
        }, error => {
          this.errorMsg = error.error.errors
        })
    } else if(this.origin == 'fournisseur') {
      this.cltfrsService.enregistrerFournisseur(this.mapToFournisseur())
        .subscribe(fournisseur => {
          this.router.navigate(['fournisseurs'])
        }, error => {
          this.errorMsg = error.error.errors
        })
    }
  }

  mapToClient(): ClientDto {
    let clientDto: ClientDto = this.clientFournisseur;
    clientDto.adresse = this.adresse
    return clientDto
  }

  mapToFournisseur(): FournisseurDto {
    let fournisseurDto: FournisseurDto = this.clientFournisseur;
    fournisseurDto.adresse = this.adresse
    return fournisseurDto
  }

  cancelClick() {
    if (this.origin === 'client') {
      this.router.navigate(['clients'])
    } else if (this.origin === 'fournisseur') {
      this.router.navigate(['fournisseurs'])
    }
  }

}
