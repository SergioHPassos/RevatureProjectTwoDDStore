import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-dropdown-button',
  templateUrl: './dropdown-button.component.html',
  styleUrls: ['./dropdown-button.component.css'],
})
export class DropdownButtonComponent implements OnInit {
  isDropped = 'hidden';
  @Input() type: string = '';
  @Input() subtypes: string[] = [];

  constructor() {}

  ngOnInit(): void {}
  toggleIsDropped(): void {
    this.isDropped = this.isDropped === 'hidden' ? '' : 'hidden';
    // setTimeout(() => {
    //   this.isDropped = this.isDropped === 'hidden' ? '' : 'hidden';
    // }, 10000);
  }
}
