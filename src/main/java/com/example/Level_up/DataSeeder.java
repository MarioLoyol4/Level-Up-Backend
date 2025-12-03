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
                                "PlayStation 5",
                                "Consolas",
                                "Consola de última generación de Sony.",
                                549990.0,
                                "https://gmedia.playstation.com/is/image/SIEPDC/ps5-product-thumbnail-01-en-14sep21"
                        ),
                        new Producto(
                                "AC001",
                                "Control Xbox Series X",
                                "Accesorios",
                                "Mando inalámbrico Xbox Series X.",
                                59990.0,
                                "https://png.pngtree.com/png-clipart/20240901/original/pngtree-wireless-xbox-controller-on-white-png-image_15908916.png"
                        ),
                        new Producto(
                                "JM001",
                                "Catan",
                                "Juegos de Mesa",
                                "El clásico juego de estrategia.",
                                34990.0,
                                "https://www.pngfind.com/pngs/m/224-2240848_catan-board-game-hd-png-download.png"
                        ),
                        new Producto(
                                "SG001",
                                "Secretlab Titan Evo",
                                "Sillas Gamers",
                                "Silla ergonómica de nivel profesional.",
                                389990.0,
                                "https://png.pngtree.com/png-vector/20240709/ourlarge/pngtree-black-gaming-chair-png-image_13050960.png"
                        )
                ));
                System.out.println("---DATOS DE PRUEBA CARGADOS EN BASE DE DATOS---");
            } else {
                System.out.println("---LA DB NO ESTABA VACIA---");
            }
        };
    }
}
