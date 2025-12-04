package com.example.Level_up.dto;


import lombok.Data;

import java.util.List;

@Data
public class CompraRequest {
    private List<ProductoCompra> productos;

    @Data
    public static class ProductoCompra {
        private String productoId;
        private Integer cantidad;
    }
}
