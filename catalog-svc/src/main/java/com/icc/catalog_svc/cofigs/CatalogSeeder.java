
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
        Store store1 = new Store(null, "Burger King", "Comida rapida","https://i.imgur.com/n93fT8S.jpeg", 4.5, "Santiago");
        Store store2 = new Store(null, "Chinese Food Restaurant", "Comida asiática","https://i.imgur.com/cPxj5V1.png", 4.0, "Talca");
        Store store3 = new Store(null, "Italian Restaurant", "Comida italiana","https://i.imgur.com/7iOQC8U.jpeg", 3.5, "Curicó");
        Store store4 = new Store(null, "Chiwake", "Comida peruana","https://i.imgur.com/eLGHFRg.jpeg", 3.0, "Curicó");
        Store store5 = new Store(null, "Tienda Populeras", "Comida casera","https://i.imgur.com/MC9Xqe3.png", 2.0, "Molina");



        storeRepository.saveAll(Arrays.asList(store1, store2, store3, store4, store5));

        Product product1= new Product (null, "King Italiano con queso", "https://i.imgur.com/ManUVvM.png",5000, store1);
        Product product2= new Product (null, "Papas fritas", "https://i.imgur.com/7Q3c9Uf.png",2500, store1);
        Product product3= new Product (null, "Nuggets", "https://i.imgur.com/i0PwXCC.png",2000, store1);
        Product product4= new Product (null, "Pie de Chocolate", "https://i.imgur.com/ZOpbGYL.png",3000, store1);
        Product product5= new Product (null, "Stacker Doble", "https://i.imgur.com/e3FG9WZ.png",8000, store1);

        Product product6= new Product (null, "Arroz chaufan", "https://i.imgur.com/cCyZFTr.jpeg",5000, store2);
        Product product7= new Product (null, "Carne Mongoliana", "https://i.imgur.com/ovkzjJF.png",2500, store2);
        Product product8= new Product (null, "Chapsui de Pollo", "https://i.imgur.com/F1bIlG0.jpeg",2000, store2);
        Product product9= new Product (null, "Sushi", "https://i.imgur.com/wSAlTQN.png",12000, store2);
        Product product10= new Product (null, "Wantan Frito 6 unidades", "https://i.imgur.com/YJ4sID2.png",1000, store2);

        Product product11 = new Product(null, "Pizza Margherita", "https://i.imgur.com/3jRmDAk.png", 5000, store3);
        Product product12 = new Product(null, "Lasagna", "https://i.imgur.com/3QOFnI8.png", 2500, store3);
        Product product13 = new Product(null, "Spaghetti Bolognese", "https://i.imgur.com/6rt0FEl.png", 2000, store3);
        Product product14 = new Product(null, "Ravioli", "https://i.imgur.com/qzUs0vi.png", 12000, store3);
        Product product15 = new Product(null, "Focaccia", "https://i.imgur.com/F3ZOw0z.png", 8000, store3);

        Product product16 = new Product(null, "Ceviche", "https://i.imgur.com/h7CkGZm.png", 4500, store4);
        Product product17 = new Product(null, "Lomo Saltado", "https://i.imgur.com/Laj9lMl.png", 4000, store4);
        Product product18 = new Product(null, "Aji de Gallina", "https://i.imgur.com/WiFMGp4.png", 6000, store4);
        Product product19 = new Product(null, "Pachamanca", "https://i.imgur.com/bTNMLGe.png", 7000, store4);
        Product product20 = new Product(null, "Empanada Peruana", "https://i.imgur.com/nCoLijv.png", 2500, store4);

        Product product21 = new Product(null, "Charquicán", "https://i.imgur.com/q7wbUTv.png", 4500, store5);
        Product product22 = new Product(null, "Porotos con riendas", "https://i.imgur.com/aLcC646.jpeg", 4000, store5);
        Product product23 = new Product(null, "Cazuela de vacuno", "https://i.imgur.com/xLBepvh.png", 6000, store5);
        Product product24 = new Product(null, "Pastel de choclo", "https://i.imgur.com/GGWOeNZ.png", 7000, store5);
        Product product25 = new Product(null, "Empanada de pino", "https://i.imgur.com/c1HCEHa.png", 2500, store5);

        productRepository.saveAll(Arrays.asList(
                product1, product2, product3, product4, product5,
                product6, product7, product8, product9, product10,
                product11, product12, product13, product14, product15,
                product16, product17, product18, product19, product20,
                product21, product22, product23, product24, product25
        ));

    }

}