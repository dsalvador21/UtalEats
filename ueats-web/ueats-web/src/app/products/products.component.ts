import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule],
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: any[] = [];
  loading: boolean = true;
  errorMessage: string = '';

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const storeId = localStorage.getItem('storeId');
    if (storeId != null) {
      this.loadProducts(storeId);
    }
  }

  loadProducts(storeId: string): void {
    const apiUrl = `http://localhost:8082/product?storeId=${storeId}`;

    this.http.get<any[]>(apiUrl).subscribe({
      next: (data) => {

        this.products = (data || []).map((product) => ({
          ...product,
          storeId: product.storeId || storeId,
          productAdded: false,
        }));
        this.loading = false;
      },
      error: (error) => {
        console.error('Error al cargar los productos:', error);
        this.errorMessage = 'No se pudieron cargar los productos.';
        this.loading = false;
      }
    });
  }


  addToCart(product: any): void {
    let currentCart = JSON.parse(localStorage.getItem('cart') || '[]');
    const cartStoreId = localStorage.getItem('cartStoreId');


    if (!product.storeId) {
      console.error('El producto no tiene un storeId definido:', product);
      alert('Error: El producto no pertenece a ninguna tienda.');
      return;
    }

    if (currentCart.length === 0) {

      currentCart.push({ ...product, quantity: 1 });
      localStorage.setItem('cart', JSON.stringify(currentCart));
      localStorage.setItem('cartStoreId', product.storeId.toString());
      product.productAdded = true;
      setTimeout(() => {
        product.productAdded = false;
      }, 1000);
      console.log('Primer producto añadido al carrito:', product);
      return;
    }

    if (cartStoreId && cartStoreId === product.storeId.toString()) {

      currentCart.push({ ...product, quantity: 1 });
      localStorage.setItem('cart', JSON.stringify(currentCart));
      product.productAdded = true;
      setTimeout(() => {
        product.productAdded = false;
      }, 1000);
      console.log('Producto añadido al carrito:', product);
    } else {

      alert('No puedes agregar productos de diferentes tiendas al carrito.');
    }
  }
  addMultipleToCart(product: any, quantity: number): void {
    if (quantity <= 0) {
      alert('La cantidad debe ser mayor a 0.');
      return;
    }

    let currentCart = JSON.parse(localStorage.getItem('cart') || '[]');
    const cartStoreId = localStorage.getItem('cartStoreId');

    if (!product.storeId) {
      console.error('El producto no tiene un storeId definido:', product);
      alert('Error: El producto no pertenece a ninguna tienda.');
      return;
    }

    if (currentCart.length === 0) {
      currentCart.push({ ...product, quantity });
      localStorage.setItem('cart', JSON.stringify(currentCart));
      localStorage.setItem('cartStoreId', product.storeId.toString());
      product.productAdded = true;
      setTimeout(() => {
        product.productAdded = false;
      }, 1000);
      console.log(`${quantity} unidades del producto añadido al carrito:`, product);
      return;
    }

    if (cartStoreId && cartStoreId === product.storeId.toString()) {
      const existingProduct = currentCart.find((item: any) => item.id === product.id);
      if (existingProduct) {
        existingProduct.quantity += quantity;
      } else {
        currentCart.push({ ...product, quantity });
      }
      localStorage.setItem('cart', JSON.stringify(currentCart));
      product.productAdded = true;
      setTimeout(() => {
        product.productAdded = false;
      }, 1000);
      console.log(`${quantity} unidades del producto añadido al carrito:`, product);
    } else {
      alert('No puedes agregar productos de diferentes tiendas al carrito.');
    }
  }




}
