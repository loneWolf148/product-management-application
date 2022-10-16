package com.example.product.app.controller;

import com.example.product.app.model.Product;
import com.example.product.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("springresttest")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("prids")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("prid/{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        return productService.getProduct(id);
    }

    @PostMapping("prid/create")
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PatchMapping("prid/update")
    public Product updateProduct(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }
}
