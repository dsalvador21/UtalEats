import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CommonModule } from "@angular/common";
import {environment} from "../../environment";

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  standalone: true,
  imports: [CommonModule],
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit {
  orderHistory: {
    AccountId: any;
    OrderId: any;
    showDetails: boolean;
    StoreId: any;
    storeName?: string; // Campo para guardar el nombre de la tienda
    ProductsIds: any[];
    productDetails?: any[];
  }[] = [];

  loading: boolean = true;
  errorMessage: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadOrderHistory();
  }

  // Cargar historial de pedidos
  loadOrderHistory(): void {
    const apiUrl = `${environment["8083"]}/order`;
    const accountId = localStorage.getItem('accountId');

    if (!accountId) {
      console.error('No se encontró el accountId en localStorage.');
      this.errorMessage = 'No se pudo cargar el historial de pedidos. Por favor, inicia sesión nuevamente.';
      this.loading = false;
      return;
    }

    const params = new HttpParams().set('accountId', accountId);

    this.http.get<any[]>(apiUrl, { params }).subscribe({
      next: (data) => {
        if (!Array.isArray(data)) {
          console.error('La respuesta no es un arreglo:', data);
          this.errorMessage = 'El formato de los datos recibidos es incorrecto.';
          this.loading = false;
          return;
        }

        this.orderHistory = data.map((order, index) => ({
          AccountId: order.accountId || null,
          OrderId: order.orderId || null,
          StoreId: order.storeId || null,
          ProductsIds: order.productsIds || [],
          showDetails: false
        }));

        // Cargar nombres de tiendas después de procesar la lista de órdenes
        this.orderHistory.forEach((order, index) => {
          if (order.StoreId) {
            this.loadStoreName(order.StoreId, index);
          }
        });

        this.loading = false;
      },
      error: (error) => {
        console.error('Error al cargar el historial de pedidos:', error);
        this.errorMessage = 'No se pudo cargar el historial de pedidos.';
        this.loading = false;
      }
    });
  }

  loadStoreName(storeId: any, index: number): void {
    const stores = localStorage.getItem('stores');
    if (stores) {
      const storeData = JSON.parse(stores);
      const filteredStore = storeData.find((store: any) => store.id === storeId);
      console.log(filteredStore.name);
      this.orderHistory[index].storeName = filteredStore.name;
    }
  }

  // Alternar visibilidad de los detalles de un pedido
  toggleDetails(index: number): void {
    const order = this.orderHistory[index];
    if (!order.productDetails) {
      // Si aún no se han cargado los detalles, realiza la llamada
      this.loadProductDetails(order.StoreId, order.ProductsIds, index);
    } else {
      // Alterna la visibilidad si ya están cargados
      order.showDetails = !order.showDetails;
    }
  }

  // Cargar detalles de los productos
  loadProductDetails(storeId: any, productIds: any[], index: number): void {
    const apiUrl = `${environment["8082"]}/product`;
    const params = new HttpParams().set('storeId', storeId);

    this.http.get<any[]>(apiUrl, { params }).subscribe({
      next: (products) => {
        if (!Array.isArray(products)) {
          console.error('La respuesta de productos no es un arreglo:', products);
          this.errorMessage = 'El formato de los datos de productos es incorrecto.';
          return;
        }

        // Filtrar productos que coincidan con los IDs del pedido
        const matchedProducts = products.filter(product =>
          productIds.includes(product.id)
        );

        this.orderHistory[index].productDetails = matchedProducts;
        this.orderHistory[index].showDetails = true; // Mostrar detalles después de cargarlos
      },
      error: (error) => {
        console.error(`Error al cargar productos para la tienda ${storeId}:`, error);
        this.errorMessage = 'No se pudieron cargar los detalles de los productos.';
      }
    });
  }

  // Calcular el precio total del pedido
  calculateTotalPrice(index: number): number {
    const order = this.orderHistory[index];
    if (!order.productDetails) {
      return 0;
    }
    return order.productDetails.reduce((total, product) => total + (product.price || 0), 0);
  }
}
