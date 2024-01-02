package org.scaler.projectservice.dtos;

import lombok.Getter;
import lombok.Setter;

//This class contains the data which we will receive from FakeStore site
//All the data types should be same as what we receiving in JSON format from the site.

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
