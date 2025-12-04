package com.example.Level_up.service;

import com.example.Level_up.dto.CompraRequest;
import com.example.Level_up.model.Boleta;
import com.example.Level_up.model.DetalleBoleta;
import com.example.Level_up.model.Producto;
import com.example.Level_up.model.Usuario;
import com.example.Level_up.repository.BoletaRepository;
import com.example.Level_up.repository.ProductoRepository;
import com.example.Level_up.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {
    private final BoletaRepository boletaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Boleta realizarVenta(CompraRequest request){
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Boleta boleta = new Boleta();
        boleta.setFecha(LocalDateTime.now());
        boleta.setUsuario(usuario);
        boleta.setTotal(0.0);

        List<DetalleBoleta> detalles = new ArrayList<>();
        double totalCalculado = 0;

        for (CompraRequest.ProductoCompra item : request.getProductos()){
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(()-> new RuntimeException("Producto no encontrado id: "+ item.getProductoId()));

            DetalleBoleta detalle = new DetalleBoleta();
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio() * item.getCantidad());
            detalle.setBoleta(boleta);

            detalles.add(detalle);
            totalCalculado += detalle.getSubtotal();
        }
        boleta.setDetalles(detalles);
        boleta.setTotal(totalCalculado);
        return boletaRepository.save(boleta);
    }
}
