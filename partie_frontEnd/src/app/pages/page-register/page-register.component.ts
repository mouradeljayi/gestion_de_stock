import { Component, OnInit } from '@angular/core';
import {EntrepriseDto} from "../../../gs-api/src/models/entreprise-dto";
import {EntrepriseService} from "../../services/entreprise/entreprise.service";
import {AdresseDto} from "../../../gs-api/src/models/adresse-dto";
import {UserService} from "../../services/user/user.service";
import {AuthenticationRequest} from "../../../gs-api/src/models/authentication-request";
import {Router} from "@angular/router";

@Component({
  selector: 'app-page-register',
  templateUrl: './page-register.component.html',
  styleUrls: ['./page-register.component.css']
})
export class PageRegisterComponent implements OnInit {

  entrepriseDto: EntrepriseDto =  {}
  adresse: AdresseDto =  {}
  errorMessages : Array<string> = []

  constructor(
    private entrepriseService: EntrepriseService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  register() : void {
    this.entrepriseDto.adresse = this.adresse
    this.entrepriseService.register(this.entrepriseDto)
      .subscribe(entrepriseDto => {
        this.connectEntreprise()
      },error => {
        this.errorMessages = error.error.errors
      })
  }

  connectEntreprise(): void {
    const authRequest: AuthenticationRequest =  {
      login: this.entrepriseDto.email,
      password: 'password'
    }
    this.userService.login(authRequest)
      .subscribe(response => {
        this.userService.setConnectedUser(response)
        this.router.navigate(['changerpassword'])
      })
  }
}
