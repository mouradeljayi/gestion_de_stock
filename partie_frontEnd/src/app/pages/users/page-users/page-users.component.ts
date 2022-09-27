import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-page-users',
  templateUrl: './page-users.component.html',
  styleUrls: ['./page-users.component.css']
})
export class PageUsersComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  nouvelUser() {
    this.router.navigate(['nouveluser'])
  }
}
