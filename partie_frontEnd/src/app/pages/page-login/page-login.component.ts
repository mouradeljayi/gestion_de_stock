import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {AuthenticationRequest} from "../../../gs-api/src/models/authentication-request";
import {Router} from "@angular/router";

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.css']
})
export class PageLoginComponent implements OnInit {

  authRequest: AuthenticationRequest = {}
  errorMessage = ""

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.login(this.authRequest)
      .subscribe((data) => {
        this.userService.setConnectedUser(data)
        this.router.navigate([''])
      }, error => {
        this.errorMessage = 'Login ou mot de passe incorrect'
      })
  }

}
