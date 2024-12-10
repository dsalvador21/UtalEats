import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule} from "@angular/common";
import {environment} from "../../environment";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  email: string = '';
  password: string = '';
  name: string = '';
  phone: string = '';
  address: string = '';
  cities: string[] = [
    'Talca',
    'Curicó',
    'Molina',
    'Santiago'
  ];

  constructor(private router: Router, private http: HttpClient) {}

  onRegister() {
    const apiUrl = `${environment["8081"]}/account/register`;

    this.http.post<any>(apiUrl, {
      email: this.email,
      password: this.password,
      name: this.name,
      phone: this.phone,
      address: this.address
    }).subscribe(
      response => {
        // Guardamos la ciudad en el almacenamiento local después del registro
        console.log(response);
        localStorage.setItem('address', this.address);
        this.router.navigate(['/']);
      },
      error => {
        alert('Registration failed');
      }
    );
  }
}
