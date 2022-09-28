import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-nouvel-clt-frs',
  templateUrl: './nouvel-clt-frs.component.html',
  styleUrls: ['./nouvel-clt-frs.component.css']
})
export class NouvelCltFrsComponent implements OnInit {

  origin = ''


  constructor(private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data['origin']
    })
  }

  saveClick() {
  }

  cancelClick() {
    if (this.origin === 'client') {
      this.router.navigate(['clients'])
    } else if (this.origin === 'fournisseur') {
      this.router.navigate(['fournisseurs'])
    }
  }

}
