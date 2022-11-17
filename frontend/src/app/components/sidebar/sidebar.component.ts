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
      armour: 'ARMOUR',
      shield: 'SHIELD',
      potion: 'POTION',
    },
    subtypes: {
      sword: ['DAGGER', 'SHORT', 'LONG', 'RAPIER', 'TWO-HANDED', 'GIANT ONLY'],
      armour: ['HEAVY', 'MEDIUM', 'LIGHT', 'MAGIC ROBES', 'GIANT ONLY'],
      shield: [ 'BUCKLER','KITE', 'HEATER', 'TOWER','GIANT ONLY'],
      potion: ['HEALING', 'MANA', 'BUFF'],
    },
  };

  constructor() {}

  ngOnInit(): void {}
}
