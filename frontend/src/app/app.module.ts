import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CartComponent } from './components/cart/cart.component';
import { FrontComponent } from './components/front/front.component';
import { ProductComponent } from './components/product/product.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { UserComponent } from './components/user/user.component';
import { LogregComponent } from './components/logreg/logreg.component';
import { FormsModule } from '@angular/forms';
import { CartproductComponent } from './components/cartproduct/cartproduct.component';
import { CardComponent } from './components/card/card.component';
import { LoginComponent } from './components/login/login.component';
import { DropdownButtonComponent } from './components/dropdown-button/dropdown-button.component';
import { DisplayProductsComponent } from './components/display-products/display-products.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontComponent,
    ProductComponent,
    CartComponent,
    SidebarComponent,
    NavbarComponent,
    UserComponent,
    LogregComponent,
    CartproductComponent,
    CardComponent,
    LoginComponent,
    DropdownButtonComponent,
    DisplayProductsComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
