import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CatalogComponent } from './catalog/catalog.component';
import {ProductsComponent} from "./products/products.component";
import {ShoppingCartComponent} from "./shoppingCart/shoppingCart.component";
import {HistorialComponent} from "./historial/historial.component";

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'catalog', component: CatalogComponent },
    { path: 'products', component: ProductsComponent},
    { path: 'shoppingCart', component: ShoppingCartComponent},
    { path: 'historial', component: HistorialComponent}
  ];
