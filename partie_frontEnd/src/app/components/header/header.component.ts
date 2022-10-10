import { Component, OnInit } from '@angular/core';
import {UtilisateurDto} from "../../../gs-api/src/models/utilisateur-dto";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  connectedUser: UtilisateurDto = {}

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.connectedUser = this.userService.getConnectedUser()
  }

}
