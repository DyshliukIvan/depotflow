package com.ivandyshliuk.depotflow.inventory;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String sku, String name) {
        if (productRepository.existsBySku(sku)) {
            throw new DublicateResourseExeption("Product with sku " + sku + " already exists");
        }

        Product product = new Product(sku, name);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
