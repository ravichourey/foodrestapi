package com.myone.foodiesapi.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.myone.foodiesapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myone.foodiesapi.entity.FoodEntity;
import com.myone.foodiesapi.io.FoodRequest;
import com.myone.foodiesapi.io.FoodResponse;
import com.myone.foodiesapi.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository repo;

    @Override
    public FoodResponse addFood(FoodRequest food, MultipartFile file) throws IOException {
        FoodEntity foodEntity = convertToEntity(food, file);
        foodEntity.setImageData(file.getBytes());
        FoodEntity foodNewEntity = repo.save(foodEntity);
        return convertToResponse(foodNewEntity);

    }

    public FoodEntity convertToEntity(FoodRequest food, MultipartFile file) {
        return FoodEntity.builder()
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .category(food.getCategory())
                .build();
    }
    public FoodResponse convertToResponse(FoodEntity food) {
        return FoodResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .category(food.getCategory())
                .imageUrl("http://localhost:8080/api/food/getFoodImage/"+food.getId())
                .build();
    }

    public List<FoodResponse> getFoods() {
        List<FoodEntity> foods = repo.findAll();
        return foods.stream().map(object -> convertToResponse(object)).collect(Collectors.toList());
    }

    public FoodResponse getFood(String id){
        FoodEntity foodExisting = repo.findById(id).orElseThrow(()-> new RuntimeException("Food not found for the id:"+ id));
        return convertToResponse(foodExisting);
    }

    public byte[] getFoodImage(String id) {
        FoodEntity food = repo.findById(id).orElseThrow(()-> new RuntimeException("Image not found for the id:"+ id));
        return food.getImageData();
    }

    public void deleteFood(String id) {
        FoodResponse food = getFood(id);
        repo.deleteById(food.getId());
    }

    public FoodResponse updateFood(String id, FoodRequest food, MultipartFile file) throws IOException {
        // Find existing food or throw exception
        FoodEntity existingFood = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + id));

        // Update basic fields
        existingFood.setName(food.getName());
        existingFood.setDescription(food.getDescription());
        existingFood.setPrice(food.getPrice());
        existingFood.setCategory(food.getCategory());

        // Update image only if new file is provided
        if (file != null && !file.isEmpty()) {
            existingFood.setImageData(file.getBytes());
        }

        // Save and return
        FoodEntity updatedFood = repo.save(existingFood);
        return convertToResponse(updatedFood);
    }

    }

