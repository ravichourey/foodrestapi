package com.myone.foodiesapi.io;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private String foodId;
    private Integer quantity; // <-- fix spelling and use Integer type
    private Double price;
    private String category;
    private String imageUrl;
    private String description;
    private String name;
}
