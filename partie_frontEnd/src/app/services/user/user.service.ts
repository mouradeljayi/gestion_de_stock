import { Injectable } from '@angular/core';
import {ApiService} from "../../../gs-api/src/services/api.service";
import {AuthenticationResponse} from "../../../gs-api/src/models/authentication-response";
import {AuthenticationRequest} from "../../../gs-api/src/models/authentication-request";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

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

  setConnectedUser(response: AuthenticationResponse) : void {
    localStorage.setItem("connectedUser", JSON.stringify(response))
  }

  // TODO
  isUserLoggedAndAccessTokenValid() : boolean {
    if (localStorage.getItem('connectedUser')) {
      // TODO
      return true
    }
    this.router.navigate(['login'])
    return false
  }

}
