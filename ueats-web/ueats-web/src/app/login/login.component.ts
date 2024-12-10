import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { environment } from "../../environment";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, NgIf],
  template: `
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">Iniciar Sesión</div>
            <div class="card-body">
              <form (ngSubmit)="onLogin()">
                <div class="mb-3">
                  <label for="email" class="form-label">Correo Electrónico</label>
                  <input
                    type="email"
                    class="form-control"
                    id="email"
                    [(ngModel)]="email"
                    name="email"
                    required
                  >
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Contraseña</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    [(ngModel)]="password"
                    name="password"
                    required
                  >
                </div>
                <button type="submit" class="btn btn-primary w-100">Iniciar Sesión</button>
              </form>
              <div *ngIf="errorMessage" class="alert alert-danger mt-3">
                {{ errorMessage }}
              </div>
              <div class="text-center mt-3">
                <p>¿No tienes una cuenta?
                  <a href="javascript:void(0)" (click)="onRegister()">Regístrate</a>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .card {
      box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
  `]
})
export class LoginComponent {
  email: string = '';
  name: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router, private http: HttpClient) {}

  onLogin() {
    if (!this.email || !this.password) {
      this.errorMessage = 'Por favor, complete todos los campos';
      return;
    }

    const apiUrl = `${environment["8081"]}/account/login`;

    // Crear los encabezados personalizados para la solicitud
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
      'Accept-Encoding': 'gzip, deflate, br',
      "ngrok-skip-browser-warning": "69420"
    });

    // Enviar la solicitud con los encabezados personalizados
    this.http.post<any>(apiUrl, {
      email: this.email,
      password: this.password,
    }, { headers }).subscribe({
      next: (loginResponse) => {
        console.log(loginResponse);
        localStorage.setItem('accountId', loginResponse.accountId);
        localStorage.setItem('email', this.email);

        const apiUrl2 = `${environment["8081"]}/profile`;

        this.http.get<any>(apiUrl2, {
          params: { accountId: loginResponse.accountId.toString() },
          headers // Reutilizamos los mismos headers aquí si es necesario
        }).subscribe({
          next: (profileResponse) => {
            console.log('Perfil obtenido:', profileResponse);
            localStorage.setItem('username', profileResponse.name);
            localStorage.setItem('address', profileResponse.address);

            window.dispatchEvent(new Event('storage'));

            this.router.navigate(['/catalog']);
          },
          error: (profileError) => {
            console.error('Error al obtener el perfil:', profileError);
            this.errorMessage = 'No se pudo cargar el perfil.';
          }
        });
      },
      error: (loginError) => {
        this.errorMessage = 'Inicio de sesión fallido. Verifique sus credenciales.';
        console.error('Error de inicio de sesión:', loginError);
      }
    });
  }

  onRegister() {
    this.router.navigate(['/register']);
  }
}
