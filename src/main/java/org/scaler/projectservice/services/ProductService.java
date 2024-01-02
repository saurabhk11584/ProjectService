package org.scaler.projectservice.services;

import org.scaler.projectservice.dtos.FakeStoreProductDto;
import org.scaler.projectservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    Product addNewProduct(FakeStoreProductDto product);
    Product deleteProduct(Long id);
    Product updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto);
    List<Product> getAllProducts();
    List<Product> getSpecificCategoryProduct(String category);
}
