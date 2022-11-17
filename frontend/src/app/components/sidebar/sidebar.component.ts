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
      potion: 'POTION',
      shield: 'SHIELD',
      travel: 'TRAVEL'
    },
    subtypes: {
      sword: ['DAGGER', 'SHORT', 'LONG', 'RAPIER', 'TWO-HANDED', 'GIANT ONLY'],
      armour: ['MAGIC ROBES', 'LIGHT', 'MEDIUM', 'HEAVY', 'GIANT ONLY'],
      shield: [ 'BUCKLER', 'TARGE', 'HEATER', 'KITE',  'TOWER','GIANT ONLY'],
      potion: ['HEALING', 'MANA', 'BUFF', 'UTILITY'],
      travel: ['CLOTHING', 'FOOD', 'SUPPLIES']
    },
  };

  constructor() {}

  ngOnInit(): void {}
}
