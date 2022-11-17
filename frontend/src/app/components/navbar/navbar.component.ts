import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  types = {
    type: {
      user: 'USER',
    },
    subtypes: {
      userB: ['Register', 'Log In', 'Wrong User? Log Off'],
    },
  };

  constructor() { }

  ngOnInit(): void {
  }

}
