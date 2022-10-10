import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ChangerMotDePasseDto} from "../../../../gs-api/src/models/changer-mot-de-passe-dto";
import {UserService} from "../../../services/user/user.service";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  changerMotDePasseDto: ChangerMotDePasseDto = {};
  ancienMotDePasse =  ''

  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    if (localStorage.getItem("origin") && localStorage.getItem("origin") === "register") {
      this.ancienMotDePasse = "password"
      localStorage.removeItem("origin")
    }
  }

  cancel() {
    this.router.navigate(['profil'])
  }

  changerMotDePasseUser() : void {
    this.changerMotDePasseDto.id = this.userService.getConnectedUser().id
    this.userService.changerMotDePasse(this.changerMotDePasseDto)
      .subscribe(data => {
        // rien faire
        this.router.navigate(['profil'])
      })
  }
}
