package com.myone.foodiesapi.services;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import com.myone.foodiesapi.io.FoodRequest;
import com.myone.foodiesapi.io.FoodResponse;

public interface FoodService {
     FoodResponse addFood(FoodRequest food, MultipartFile file) throws IOException;
}

//import com.myone.foodiesapi.io.FoodRequest;
//import com.myone.foodiesapi.io.FoodResponse;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//public interface FoodServices {
//
//     FoodResponse addFood(FoodRequest request  , MultipartFile file) throws IOException;
//}
