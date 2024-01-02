package org.scaler.projectservice.controller;

import org.scaler.projectservice.models.Product;
import org.scaler.projectservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private ProductService productService;

    public CategoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{catType}")
    public List<Product> getSpecificCategoryProduct(@PathVariable("catType")String catType) {
        return productService.getSpecificCategoryProduct(catType);
    }

}
