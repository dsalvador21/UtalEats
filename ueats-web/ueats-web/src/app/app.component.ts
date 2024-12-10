import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CatalogComponent } from './catalog/catalog.component';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterModule,
    FormsModule,
    HttpClientModule,
    NgIf
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'mercadito-web';
  isAuthenticated: boolean = false;
  username: string | null = null;
  email: string | null = null;
  dropdownOpen: boolean = false;

  constructor(private router: Router) {}

  ngOnInit() {
    this.checkAuthentication();
    window.addEventListener('storage', this.checkAuthentication.bind(this));
    document.addEventListener('click', this.closeDropdown.bind(this));
  }


  checkAuthentication = () => {
    const accountId = localStorage.getItem('accountId');
    this.isAuthenticated = !!accountId;

    if (this.isAuthenticated) {
      this.username = localStorage.getItem('username');
      this.email = localStorage.getItem('email');
    } else {
      this.username = null;
      this.email = null;
    }
  };

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  closeDropdown(event: Event) {
    const target = event.target as HTMLElement;
    if (!target.closest('.profile-container')) {
      this.dropdownOpen = false;
    }
  }

  viewStories() {
    console.log('Navegando a historias...');
    this.dropdownOpen = false;
    this.router.navigate(['/historial']);
  }

  logout() {

    //localStorage.clear();

    localStorage.removeItem('accountId');
    localStorage.removeItem('username');
    localStorage.removeItem('email');

    this.isAuthenticated = false;
    this.username = null;
    this.email = null;

    window.dispatchEvent(new Event('storage'));

    this.dropdownOpen = false;
    this.router.navigate(['/login']);
  }

  goToShoppingCart() {
    this.router.navigate(['/shoppingCart']);
  }

  goToCatalog() {
    if (this.isAuthenticated) {
      this.router.navigate(['/catalog']);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
