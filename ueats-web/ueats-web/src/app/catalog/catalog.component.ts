import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  stores: any[] = [];
  loading: boolean = true;
  errorMessage: string = '';
  selectedStoreId: number | null = null;

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    const address = localStorage.getItem('address');
    console.log('City obtenido del localStorage:', address);
    if (address) {
      this.loadStores(address);
    } else {
      this.errorMessage = 'No se pudo determinar la dirección. Por favor, regístrese nuevamente.';
      this.loading = false;
    }
  }

  loadStores(address: string): void {
    const apiUrl = 'http://localhost:8082/store';
    const params = new HttpParams().set('city', address);

    this.http.get<any[]>(apiUrl, { params }).subscribe({
      next: (data) => {
        this.stores = data.map(store => ({
          ...store,
          comments: store.comments || [],
          showComments: false,
          rating: 0
        }));


        this.stores.forEach(store => this.loadRating(store));
        localStorage.setItem('stores', JSON.stringify(this.stores));
        this.loading = false;
      },
      error: (error) => {
        console.error('Error al cargar las tiendas:', error);
        this.errorMessage = 'No se pudieron cargar las tiendas.';
        this.loading = false;
      }
    });
  }

  loadRating(store: any): void {
    const apiUrl = 'http://localhost:8084/rating/score';
    const params = new HttpParams().set('storeId', store.id);

    this.http.get<number>(apiUrl, { params }).subscribe({
      next: (rating) => {
        store.rating = rating;
      },
      error: (error) => {
        console.error(`Error al obtener el rating para la tienda ${store.id}:`, error);
        store.rating = null;
      }
    });
  }


  goToProduct(storeId: number): void {
    this.router.navigate(['/products']);
    localStorage.setItem('storeId', storeId.toString());
  }


  toggleComments(storeId: number): void {
    const apiUrl = 'http://localhost:8084/rating';
    const params = new HttpParams().set('storeId', storeId.toString());

    this.http.get<any[]>(apiUrl, { params }).subscribe({
      next: (comments) => {
        const store = this.stores.find(store => store.id === storeId);
        if (store) {
          store.comments = comments || [];
          store.showComments = !store.showComments;
          this.selectedStoreId = this.selectedStoreId === storeId ? null : storeId;
        }
      },
      error: (error) => {
        console.error('Error al obtener comentarios:', error);
      }
    });
  }
}
