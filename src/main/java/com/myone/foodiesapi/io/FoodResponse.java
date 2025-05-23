package com.myone.foodiesapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodResponse<F> {
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
}

//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.Binary;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class FoodResponse {
//
//    private String id;
//    private String name;
//    private double price;
//    private String description;
//    private String categories;
//    private Binary imageData;
//}
