package org.scaler.projectservice.controller;

import org.apache.logging.log4j.util.Strings;
import org.scaler.projectservice.dtos.FakeStoreProductDto;
import org.scaler.projectservice.models.Category;
import org.scaler.projectservice.models.Product;
import org.scaler.projectservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public  ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping  //localHost:8080/products
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }



    @PostMapping()
    public Product addNewProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto) {
        return productService.addNewProduct(fakeStoreProductDto);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto) {
        return productService.updateProduct(id, fakeStoreProductDto);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}
