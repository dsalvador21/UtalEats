import {Component, OnInit} from "@angular/core";
import { CommonModule } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import {RatingsComponent} from "../ratings/ratings.component";
import {environment} from "../../environment";

@Component({
  selector: 'app-shoppingCart',
  standalone: true,
  imports: [CommonModule, RatingsComponent, RatingsComponent],
  templateUrl: './shoppingCart.component.html',
  styleUrls: ['./shoppingCart.component.css'],
})
export class ShoppingCartComponent implements OnInit {
  cartItems: any[] = [];
  loading: boolean = true;
  errorMessage: string = '';
  storeId: string | null = null;
  totalPrice: number = 0;
  orderCompleted: boolean = false;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.storeId = localStorage.getItem('storeId');
    if (this.storeId) {
      this.loadCart();
    } else {
      this.errorMessage = 'No se ha seleccionado una tienda.';
      this.loading = false;
    }
  }

  loadCart(): void {
    const storedCart = localStorage.getItem('cart');
    console.log('Contenido de localStorage[cart]:', storedCart);
    if (storedCart) {
      this.cartItems = JSON.parse(storedCart).map((item: any) => ({
        ...item,
        removed: false,
      }));
      console.log('Carrito cargado:', this.cartItems);
      this.calculateTotalPrice();
    } else {
      this.cartItems = [];
      this.totalPrice = 0;
      console.log('El carrito está vacío.');
    }
    this.loading = false;
  }

  removeFromCart(itemId: number): void {
    const cart = JSON.parse(localStorage.getItem('cart') || '[]');

    const index = this.cartItems.findIndex((item: any) => item.id === itemId);

    if (index !== -1) {
      if (this.cartItems[index].quantity > 1) {
        this.cartItems[index].quantity--;

        const cartIndex = cart.findIndex((item: any) => item.id === itemId);
        if (cartIndex !== -1) {
          cart[cartIndex].quantity = this.cartItems[index].quantity;
        }
        localStorage.setItem('cart', JSON.stringify(cart));
      } else {
        this.cartItems[index].removed = true;
        setTimeout(() => {
          this.cartItems.splice(index, 1);
          const updatedCart = cart.filter((item: any) => item.id !== itemId);
          localStorage.setItem('cart', JSON.stringify(updatedCart));
          this.calculateTotalPrice();
        }, 1000);
      }

      this.calculateTotalPrice();

      console.log(`Producto con ID ${itemId} actualizado en el carrito.`);
    } else {
      console.log(`Producto con ID ${itemId} no encontrado en el carrito.`);
    }
  }




  checkout(): void {
    const apiUrl = `${environment["8083"]}/order`;
    if (!this.storeId) {
      this.errorMessage = 'No se ha seleccionado una tienda.';
      return;
    }

    const accountId = localStorage.getItem('accountId');
    if (!accountId) {
      this.errorMessage = 'No se ha seleccionado un usuario.';
      return;
    }

    const productsIds = this.cartItems.map(item => item.id);

    const orderDTO = {
      accountId: Number(accountId),
      storeId: Number(this.storeId),
      productsIds: productsIds
    };

    localStorage.setItem('storeId', this.storeId);

    this.http.post(apiUrl, orderDTO).subscribe({
      next: (response) => {
        console.log('Pedido registrado con éxito:', response);
        alert('Pedido realizado con éxito.');
        this.orderCompleted = true;
      },
      error: (err) => {
        console.error('Error al registrar el pedido:', err);
        this.errorMessage = 'Error al registrar el pedido. Intente nuevamente.';
      }
    });

    localStorage.removeItem('cart');
    this.cartItems = [];
    this.totalPrice = 0;
  }

  calculateTotalPrice(): void {
    this.totalPrice = this.cartItems.reduce((total, item) => {
      const quantity = item.quantity || 1;
      return total + (item.price * quantity);
    }, 0);
    console.log('Precio total calculado:', this.totalPrice);
  }

  clearCart(): void {
    const confirmClear = confirm('¿Estás seguro de que quieres vaciar completamente el carrito?');
    if (confirmClear) {
      localStorage.removeItem('cart');
      this.cartItems = [];
      this.totalPrice = 0;
      console.log('Carrito vaciado completamente');
    }
  }

  onRatingSubmitted(rating: number): void {
    console.log(`El usuario calificó la tienda con ${rating} estrellas.`);
    alert('¡Gracias por tu calificación!');
  }
}
