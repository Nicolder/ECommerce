package com.ecommerce.ecommerceapi.web;

import com.ecommerce.ecommerceapi.model.Product;
import com.ecommerce.ecommerceapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // 1. Anotação principal para criar um controller REST
@RequestMapping("/api/products") // 2. Define a URL base para todos os endpoints nesta classe
public class ProductController {

    // 3. Injeção de Dependência via construtor (melhor prática)
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // --- ENDPOINTS

    // 4. Endpoint para CRIAR um novo produto (HTTP POST)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED); // Retorna 201 Created
    }

    // 5. Endpoint para LISTAR TODOS os produtos (HTTP GET)
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 6. Endpoint para BUSCAR UM produto por ID (HTTP GET)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        // Se o produto existir, retorna 200 OK com o produto. Senão, retorna 404 Not Found.
        return productOptional
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }
}