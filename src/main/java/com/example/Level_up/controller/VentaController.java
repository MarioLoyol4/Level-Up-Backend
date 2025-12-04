package com.example.Level_up.controller;


import com.example.Level_up.dto.CompraRequest;
import com.example.Level_up.model.Boleta;
import com.example.Level_up.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;

    @PostMapping("/comprar")
    public ResponseEntity<Boleta> comprar(@RequestBody CompraRequest request) {
        Boleta nuevaBoleta = ventaService.realizarVenta(request);
        return ResponseEntity.ok(nuevaBoleta);
    }
}
