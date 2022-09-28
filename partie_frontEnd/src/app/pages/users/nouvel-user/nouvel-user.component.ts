import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nouvel-user',
  templateUrl: './nouvel-user.component.html',
  styleUrls: ['./nouvel-user.component.css']
})
export class NouvelUserComponent implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  cancel() {
    this.router.navigate(['users'])
  }
}
