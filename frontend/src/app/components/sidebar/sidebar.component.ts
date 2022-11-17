import { Component, Injectable, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  types = {
    type: {
      sword: 'SWORD',
      shield: 'SHIELD',
      potion: 'POTION',
    },
    subtypes: {
      sword: ['DAGGER', 'SHORT', 'LONG'],
      shield: ['USELESS', 'LARGE', 'GIANT ONLY'],
      potion: ['HEALING', 'MANA', 'BUFF'],
    },
  };

  constructor() {}

  ngOnInit(): void {}
}
