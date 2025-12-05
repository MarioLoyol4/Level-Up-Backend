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
                                1,
                                "PlayStation 5",
                                "Consolas",
                                549990.0,
                                "https://i5.walmartimages.cl/asr/e0ba681e-77a6-4ad2-aa5f-a9ecee747292.126d85165f67961f59315dd5ffe5be61.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF"
                        ),
                        new Producto(
                                2,
                                "Control Xbox Series X",
                                "Accesorios",
                                59990.0,
                                "https://png.pngtree.com/png-clipart/20240901/original/pngtree-wireless-xbox-controller-on-white-png-image_15908916.png"
                        ),
                        new Producto(
                                3,
                                "Catan",
                                "Juegos de Mesa",
                                34990.0,
                                "https://pendulo.com/imagenes_grandes/8436017/843601722010.GIF"
                        ),
                        new Producto(
                                4,
                                "Secretlab Titan Evo",
                                "Sillas Gamers",
                                389990.0,
                                "https://images-na.ssl-images-amazon.com/images/I/51HHjm67W4L._AC_UL495_SR435,495_.jpg"
                        ),
                        new Producto(
                                5,
                                "Carcassonne",
                                "juegos-mesa",
                                15000.0,
                                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSk_fV2gMez_V20qjGgjdqOCY4j-C4oxN_v8A&s"

                        ),
                        new Producto(
                                6,
                                "Cashflow",
                                "juegos-mesa",
                                25000.0,
                                "https://play-lh.googleusercontent.com/5wVf_t7ngsvAmH9WQmB40QCP_SiuKK6G2s8dS6TtjPN_g87Kf0LoRFD3GsYXAzwIwkk=w526-h296-rw"
                        ),
                        new Producto(
                                7,
                                "Silla Gamer Azul",
                                "sillas",
                                85000.0,
                                "https://cl-cenco-pim-resizer.ecomm.cencosud.com/unsafe/adaptive-fit-in/3840x0/filters:quality(75)/prd-cl/product-medias/MK0P5BRMO3/f3e1bb3f-ebe8-49b7-8581-88b85d6c99a2/1688625293290-MK0P5BRMO3-1-1.webp"
                        ),
                        new Producto(
                         8,
                        "Silla Gamer Celeste",
                        "sillas",
                        85000.0,
                        "https://http2.mlstatic.com/D_NQ_NP_635728-MLA74806225271_022024-O.webp"
                        ),
                        new Producto(
                                9,
                                "Mouse Pad Rgb Xl",
                                "Accesorios",
                                10990.0,
                                "https://store.capcom.cl/wp-content/uploads/2021/11/fgd-02-rgb-gaming-mouse-pad-myra-original-imag7hvbjfzmg35c.jpeg"
                        ),
                        new Producto(
                                10,
                                "Audifonos Gamer Hyperx",
                                "Accesorios",
                                69990.0,
                                "https://www.ebest.cl/media/catalog/product/cache/47abc4af9d81a631bd44d97ba9797770/h/y/hyperx-cloud-stinger-5.jpg"
                        )



                ));
                System.out.println("---DATOS DE PRUEBA CARGADOS EN BASE DE DATOS---");
            } else {
                System.out.println("---LA DB NO ESTABA VACIA---");
            }
        };
    }
}
