import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-page-cmd-clt-frs',
  templateUrl: './page-cmd-clt-frs.component.html',
  styleUrls: ['./page-cmd-clt-frs.component.css']
})
export class PageCmdCltFrsComponent implements OnInit {

  origin = ''

  constructor(private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data['origin']
    })
  }

  nouvelleCommande() {
    if (this.origin === 'clients') {
      this.router.navigate(['nouvellecommandeclient'])
    } else if (this.origin === 'fournisseurs') {
      this.router.navigate(['nouvellecommandefournisseur'])
    }
  }
}
