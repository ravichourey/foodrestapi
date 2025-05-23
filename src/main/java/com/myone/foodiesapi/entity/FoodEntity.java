package com.myone.foodiesapi.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "foods")
public class FoodEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private byte[] imageData;
}




//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.Binary;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Document (collection = "foods")
//public class FoodEntity {
//    @Id
//    private String id;
//    private String name;
//    private double price;
//    private String description;
//    private String categories;
//    private Binary imageData;
//}
