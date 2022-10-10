import { Injectable } from '@angular/core';
import {ApiService} from "../../../gs-api/src/services/api.service";
import {AuthenticationResponse} from "../../../gs-api/src/models/authentication-response";
import {AuthenticationRequest} from "../../../gs-api/src/models/authentication-request";
import {Observable, of} from "rxjs";
import {Router} from "@angular/router";
import {UtilisateurDto} from "../../../gs-api/src/models/utilisateur-dto";
import {ChangerMotDePasseDto} from "../../../gs-api/src/models/changer-mot-de-passe-dto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private service: ApiService,
    private router: Router
  ) { }

  login(request: AuthenticationRequest): Observable<AuthenticationResponse> {
   return this.service.authenticate(request)
  }

  getUserByEmail(email?: string): Observable<UtilisateurDto> {
    if (email !== undefined) {
      return this.service.findByEmail(email)
    }
    return of();
  }

  setAccessToken(response: AuthenticationResponse) : void {
    localStorage.setItem("accessToken", JSON.stringify(response))
  }

  setConnectedUser(user: UtilisateurDto) : void {
    localStorage.setItem("connectedUser", JSON.stringify(user))
  }

  getConnectedUser() : UtilisateurDto {
    if (localStorage.getItem("connectedUser")) {
      return JSON.parse(localStorage.getItem("connectedUser") as string)
    }
    return {}
  }

  changerMotDePasse(changerMotDePasse: ChangerMotDePasseDto) : Observable<ChangerMotDePasseDto> {
    return this.service.changerMotDePasse(changerMotDePasse)
  }

  // TODO
  isUserLoggedAndAccessTokenValid() : boolean {
    if (localStorage.getItem('accessToken')) {
      // TODO
      return true
    }
    this.router.navigate(['login'])
    return false
  }

}
