import { Component, Injectable, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  types = {
    type: {
      weapons: 'WEAPONS',
      armor: 'ARMOR',
      jewelry: 'JEWELRY',
      potions: 'POTIONS',
      gear: 'GEAR',
      companions: 'COMPANIONS'
    },
    subtypes: {
      weapons: ['Longswords',
      'Great Swords',
      'Shortswords',
      'Longbows',
      'Shortbows',
      'Daggers',
      'Axes',
      'Katanas',
      'Bludgeoning',
      'Rapiers',
      'Polearms'],
      armor: ['Headwear',
      'Chestpieces',
      'Shields'],
      jewelry: ['Bangles',
      'Rings'],
      potions: ['Healing Potions',
      'Transmutation Potions',
      'Abjuration Potions',
      'Mana Potions',
      'Dual Potions'],
      gear: ['Travel'],
      companions: ['Pets']
    },
  };

  constructor() {}

  ngOnInit(): void {}
}
