import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationResponse} from "../../../gs-api/src/models/authentication-response";

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authResponse: AuthenticationResponse = {};
    if (localStorage.getItem("accessToken")) {
      authResponse = JSON.parse(
        localStorage.getItem("accessToken") as string
      )
      const authReq = req.clone({
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + authResponse.accessToken
        })
      });
      return next.handle(authReq)
    }
    return next.handle(req)
  }
}
