package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.model.Product;
import com.ecommerce.ecommerceapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product salvar(Product product) {
        // Aqui você poderia adicionar lógicas, como validar se um produto com mesmo nome já existe
        return productRepository.save(product);
    }

    public List<Product> listarTodos() {
        return productRepository.findAll();
    }

    public Optional<Product> buscarPorId(Long id) {
        return productRepository.findById(id);
    }

    public Product editarProduto(Long id, Product productDetails) {
        // Primeiro, busca o produto existente
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Atualiza os campos do produto existente com os detalhes recebidos
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setImageUrl(productDetails.getImageUrl());
        existingProduct.setCategory(productDetails.getCategory());
        existingProduct.setActive(productDetails.isActive());

        // Salva o produto atualizado
        return productRepository.save(existingProduct);
    }

    public void Deletar(Long id) {
        // Verifica se o produto existe antes de deletar
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}