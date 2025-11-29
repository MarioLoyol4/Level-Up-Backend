package com.example.Level_up;

import com.example.Level_up.model.Producto;
import com.example.Level_up.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(ProductoRepository repository) {
        return args -> {
            System.out.println("---INICIANDO DATA SEEDER---");

            long cantidad = repository.count();
            System.out.println("---PRODUCTOS EN DB: "+ cantidad + "---");
            if (repository.count() == 0){
                repository.saveAll(Arrays.asList(
                        new Producto(
                                "CO001",
                                "PlayStation 5",       // Nombre
                                "Consolas",            // Categoría
                                "Consola de última generación de Sony.",
                                549990.0,
                                "https://gmedia.playstation.com/is/image/SIEPDC/ps5-product-thumbnail-01-en-14sep21"
                        ),
                        new Producto(
                                "AC001",
                                "Control Xbox Series X", // Nombre
                                "Accesorios",            // Categoría
                                "Mando inalámbrico Robot White.",
                                59990.0,
                                "https://compass-ssl.xbox.com/assets/26/63/2663eb43-42e7-449e-b7df-e9df27d2c332.jpg?n=Xbox-Wireless-Controller_Image-0_Robot-White_1020x574.jpg"
                        ),
                        new Producto(
                                "JM001",
                                "Catan",               // Nombre
                                "Juegos de Mesa",      // Categoría
                                "El clásico juego de estrategia.",
                                34990.0,
                                "https://images.ctfassets.net/s5n2t79q9icq/4M83Q2yJIoR6t69Y2W96Y/6292437633c36ba4e889b7b7f58d2777/CATAN_3D_box_face.png"
                        ),
                        new Producto(
                                "SG001",
                                "Secretlab Titan Evo", // Nombre
                                "Sillas Gamers",       // Categoría
                                "Silla ergonómica de nivel profesional.",
                                389990.0,
                                "https://secretlab.co/cdn/shop/products/Titan_2020_Black_01.png"
                        )
                ));
                System.out.println("---DATOS DE PRUEBA CARGADOS EN BASE DE DATOS---");
            } else {
                System.out.println("---LA DB NO ESTABA VACIA---");
            }
        };
    }
}
