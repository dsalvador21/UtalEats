import { Component, Output, EventEmitter, NO_ERRORS_SCHEMA } from '@angular/core';
import { DecimalPipe } from "@angular/common";
import { CommonModule } from "@angular/common";
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {environment} from "../../environment";

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  standalone: true,
  imports: [
    DecimalPipe,
    CommonModule,
    FormsModule
  ],
  schemas: [NO_ERRORS_SCHEMA],
  styleUrls: ['./ratings.component.css']
})

export class RatingsComponent {
  @Output() ratingSubmitted = new EventEmitter<number>();
  stars: number[] = Array(5).fill(0);
  currentRating: number = 0;
  hasRated: boolean = false;
  hoverRating = 0;
  ratings: number[] = [];
  userComment = '';
  storeId: string | null = null;
  showRatingPrompt: boolean = true;
  showRatingSection: boolean = false;

  constructor(private http: HttpClient, private router: Router) {}

  get averageRating(): number {
    if (this.ratings.length === 0) return 0;
    return this.ratings.reduce((sum, rating) => sum + rating, 0) / this.ratings.length;
  }

  rateStore(rating: number): void {
    if (!this.hasRated) {
      this.currentRating = rating;
      this.ratings.push(rating);
      this.hasRated = true;
      this.ratingSubmitted.emit(rating);
    }
  }

  updateHover(rating: number): void {
    this.hoverRating = rating;
  }

  clearHover(): void {
    this.hoverRating = 0;
  }

  submitComment(): void {
    if (this.userComment.trim()) {
      console.log('Comentario enviado:', this.userComment);
      alert('¡Gracias por tu comentario!');

      const apiUrl = `${environment["8084"]}/rating`

      this.http.post<any>(apiUrl, {

        
        accountId: localStorage.getItem('accountId'),
        storeId: localStorage.getItem('storeId'),
        score: this.currentRating,
        comment: this.userComment
      }).subscribe({
        next: (response) => {
          console.log("Comentario guardado.");
          this.userComment = '';

          // Redirigir al catálogo después de guardar la calificación
          this.router.navigate(['/catalog']);
        },
        error: (error) => {
          console.error("Error al entregar comentario.");
          alert('Hubo un error al enviar el comentario. Intenta nuevamente.');
        }
      });
    } else {
      alert('Por favor, escribe un comentario.');
    }
  }

  acceptRating(): void {
    this.showRatingPrompt = false;
    this.showRatingSection = true;
  }

  declineRating(): void {
    this.showRatingPrompt = false;
    alert('¡Gracias por hacer tu comentario!');
    this.router.navigate(['/catalog']);
  }

  closeModal(): void {
    this.showRatingSection = false;
    this.showRatingPrompt = true;
  }
}
