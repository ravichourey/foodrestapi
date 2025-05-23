package com.myone.foodiesapi.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {
    private String name;
    private String description;
    private double price;
    private String category;
}
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class FoodRequest {
//
//    private String name;
//    private double price;
//    private String description;
//    private String categories;
//}
