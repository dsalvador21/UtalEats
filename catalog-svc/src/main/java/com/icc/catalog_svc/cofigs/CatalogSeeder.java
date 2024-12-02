
package com.icc.catalog_svc.cofigs;

import com.icc.catalog_svc.models.Product;
import com.icc.catalog_svc.repositories.ProductRepository;
import com.icc.catalog_svc.models.Store;
import com.icc.catalog_svc.repositories.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CatalogSeeder implements CommandLineRunner {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductRepository productRepository;

    /*
    public CatalogSeeder(StoreRepository storeRepository, ProductRepository productRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }


     */

    @Override
    @Transactional
    public void run (String... args) throws Exception {
        if (productRepository.count() == 0 && storeRepository.count() == 0) {
            seedDatabase();
        }

    }

    public void seedDatabase(){
        System.out.println("Hola");
        Store store1 = new Store(null, "Tienda 1", "Comida rapida","urllogo", 4.5, "Santiago");
        Store store2 = new Store(null, "Tienda 2", "Asiatica","urllogo2", 4.0, "Talca");
        Store store3 = new Store(null, "Tienda 3", "Italiana","urllogo3", 3.5, "Curicó");
        Store store4 = new Store(null, "Tienda 4", "Peruana","urllogo4", 3.0, "Curicó");
        Store store5 = new Store(null, "Tienda 5", "Casera","urllogo5", 2.0, "Molina");



        storeRepository.saveAll(Arrays.asList(store1, store2, store3, store4, store5));

        Product product1= new Product (null, "Hamburguesa", "urlimagen1",5000, store1);
        Product product2= new Product (null, "Papas fritas", "urlimagen2",2500, store1);
        Product product3= new Product (null, "Nuggets", "urlimagen3",2000, store1);
        Product product4= new Product (null, "Pizza", "urlimagen4",12000, store1);
        Product product5= new Product (null, "Pollo frito", "urlimagen5",8000, store1);

        Product product6= new Product (null, "Arroz chaufan", "urlimagen6",5000, store2);
        Product product7= new Product (null, "Carne Mongoliana", "urlimagen7",2500, store2);
        Product product8= new Product (null, "Chapsui", "urlimagen8",2000, store2);
        Product product9= new Product (null, "Sushi", "urlimagen9",12000, store2);
        Product product10= new Product (null, "Wantan Frito", "urlimagen10",8000, store2);

        Product product11 = new Product(null, "Pizza Margherita", "urlimagen11", 5000, store3);
        Product product12 = new Product(null, "Lasagna", "urlimagen12", 2500, store3);
        Product product13 = new Product(null, "Spaghetti Bolognese", "urlimagen13", 2000, store3);
        Product product14 = new Product(null, "Ravioli", "urlimagen14", 12000, store3);
        Product product15 = new Product(null, "Focaccia", "urlimagen15", 8000, store3);

        Product product16 = new Product(null, "Ceviche", "urlimagen16", 4500, store4);
        Product product17 = new Product(null, "Lomo Saltado", "urlimagen17", 4000, store4);
        Product product18 = new Product(null, "Aji de Gallina", "urlimagen18", 6000, store4);
        Product product19 = new Product(null, "Pachamanca", "urlimagen19", 7000, store4);
        Product product20 = new Product(null, "Empanada Peruana", "urlimagen20", 2500, store4);

        Product product21 = new Product(null, "Charquicán", "urlimagen21", 4500, store5);
        Product product22 = new Product(null, "Porotos con riendas", "urlimagen22", 4000, store5);
        Product product23 = new Product(null, "Cazuela de vacuno", "urlimagen23", 6000, store5);
        Product product24 = new Product(null, "Pastel de choclo", "urlimagen24", 7000, store5);
        Product product25 = new Product(null, "Empanada de pino", "urlimagen25", 2500, store5);

        productRepository.saveAll(Arrays.asList(
                product1, product2, product3, product4, product5,
                product6, product7, product8, product9, product10,
                product11, product12, product13, product14, product15,
                product16, product17, product18, product19, product20,
                product21, product22, product23, product24, product25
        ));

    }

}