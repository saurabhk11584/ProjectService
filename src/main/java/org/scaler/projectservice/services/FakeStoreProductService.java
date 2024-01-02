package org.scaler.projectservice.services;

import org.scaler.projectservice.dtos.FakeStoreProductDto;
import org.scaler.projectservice.models.Category;
import org.scaler.projectservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        return product;
    }


    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class     //JSON input which we are getting from the FakeStore will have 1:1 mapping with this class
        );
        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public Product addNewProduct(FakeStoreProductDto fakeStoreProductDto) {
            if(fakeStoreProductDto == null) {
                return null;
            }
        FakeStoreProductDto productDto = restTemplate.postForObject(
                    "https://fakestoreapi.com/products",
                    fakeStoreProductDto,
                    FakeStoreProductDto.class
            );
            return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public Product deleteProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class
        ).getBody();
        if(fakeStoreProductDto != null) {
            System.out.println("Product is deleted");
            return convertFakeStoreProductToProduct(fakeStoreProductDto);
        }
        System.out.println("Product is not found");
        return null;
    }

    @Override
    public Product updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        FakeStoreProductDto updatedDto = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT,
                new HttpEntity<>(fakeStoreProductDto),
                FakeStoreProductDto.class,
                id
        ).getBody();
        System.out.println(updatedDto);
        if(updatedDto != null) {
            System.out.println("Product is updated");
            return convertFakeStoreProductToProduct(updatedDto);
        }
        System.out.println("Product is not updated");
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        if(fakeStoreProductDtos != null) {
            List<Product> products = new ArrayList<>();
            for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos) {
                products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
            }
            return products;
        }
        System.out.println("Products are not there");
        return null;
    }

    @Override
    public List<Product> getSpecificCategoryProduct(String catType) {
        System.out.println(catType);
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + catType,
                FakeStoreProductDto[].class
        );
        if(fakeStoreProductDtos != null) {
            List<Product> products = new ArrayList<>();
            for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos) {
                products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
            }
            return products;
        }
        System.out.println("Products are not there");
        return null;
    }

}
