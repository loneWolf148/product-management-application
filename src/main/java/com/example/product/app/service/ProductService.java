package com.example.product.app.service;

import com.example.product.app.exception.BusinessException;
import com.example.product.app.model.Product;
import com.example.product.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_MSG = "Product Not Found : %d";
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        LOGGER.info("Fetching all products from the repository");
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        LOGGER.info("Fetching product with id {}", id);
        return productRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(String.format(PRODUCT_NOT_FOUND_MSG, id), HttpStatus.NOT_FOUND));
    }

    public Product addProduct(Product product) {
        Product newProductToAdd = new Product();
        newProductToAdd.setName(product.getName());
        newProductToAdd.setTimestamp(System.currentTimeMillis());

        Product addedProduct = productRepository.saveAndFlush(newProductToAdd);
        LOGGER.info("New Product {} with id {} created successfully", addedProduct.getName(), addedProduct.getId());
        return addedProduct;
    }

    public Product updateProduct(Product product) {
        Product productToUpdate = productRepository
                .findById(product.getId())
                .orElseThrow(() -> new BusinessException(String.format(PRODUCT_NOT_FOUND_MSG, product.getId()), HttpStatus.NOT_FOUND));

        String oldProductName = productToUpdate.getName();
        productToUpdate.setName(product.getName() != null ? product.getName() : productToUpdate.getName());

        LOGGER.info("Product name updated from {} to {} for product id {}", oldProductName, productToUpdate.getName(), productToUpdate.getId());
        return productRepository.saveAndFlush(productToUpdate);
    }
}