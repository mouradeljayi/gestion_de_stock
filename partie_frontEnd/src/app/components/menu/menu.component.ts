import { Component, OnInit } from '@angular/core';
import {Menu} from "./menu";
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public menuProperties: Array<Menu> = [
    {
      id: '1',
      title: 'Tableau de bord',
      icon: 'fa-solid fa-house-user',
      url: '',
      sousMenu: [
        {
          id: '11',
          title: 'Vue d\'ensemble',
          icon: 'fa-solid fa-chart-pie',
          url: '',
        },
        {
          id: '12',
          title: 'Statistiques',
          icon: 'fa-solid fa-chart-simple',
          url: 'statistiques',
        },
      ]
    },
    {
      id: '2',
      title: 'Articles',
      icon: 'fa-solid fa-boxes',
      url: '',
      sousMenu: [
        {
          id: '21',
          title: 'Articles',
          icon: 'fa-solid fa-boxes',
          url: 'articles',
        },
        {
          id: '22',
          title: 'Mouvements du stock',
          icon: 'fa-brands fa-stack-overflow',
          url: 'mouvementstock',
        },
      ]
    },
    {
      id: '3',
      title: 'Clients',
      icon: 'fa-solid fa-users',
      url: '',
      sousMenu: [
        {
          id: '31',
          title: 'Clients',
          icon: 'fa-solid fa-users',
          url: 'clients',
        },
        {
          id: '32',
          title: 'Commandes clients',
          icon: 'fa-solid fa-shopping-basket',
          url: 'commandeclient',
        },
      ]
    },
    {
      id: '4',
      title: 'Fournisseurs',
      icon: 'fa-solid fa-users',
      url: '',
      sousMenu: [
        {
          id: '41',
          title: 'Fournisseurs',
          icon: 'fa-solid fa-users',
          url: 'fournisseurs',
        },
        {
          id: '42',
          title: 'Commandes fournisseurs',
          icon: 'fa-solid fa-truck',
          url: 'commandefournisseur',
        },
      ]
    },
    {
      id: '5',
      title: 'Parametrages',
      icon: 'fa-solid fa-cogs',
      url: '',
      sousMenu: [
        {
          id: '51',
          title: 'Categories',
          icon: 'fa-solid fa-list',
          url: 'categories',
        },
        {
          id: '52',
          title: 'Utilisateurs',
          icon: 'fa-solid fa-users',
          url: 'users',
        },
      ]
    },
  ];

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  navigate(url?: string) {
    this.router.navigate([url]);
  }
}
