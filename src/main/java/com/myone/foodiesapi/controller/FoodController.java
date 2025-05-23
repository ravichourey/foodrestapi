package com.myone.foodiesapi.controller;

import java.io.IOException;
import java.util.List;

import com.myone.foodiesapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.myone.foodiesapi.io.FoodRequest;
import com.myone.foodiesapi.io.FoodResponse;
import com.myone.foodiesapi.services.FoodServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/food")
@CrossOrigin("*")
public class FoodController {

    @Autowired
    FoodServiceImpl service;

    @PostMapping("/add-food")
    private ResponseEntity<FoodResponse> addFood(@RequestPart("food") String foodJson, @RequestPart("file") MultipartFile file ) throws IOException {
        FoodResponse food1 = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            FoodRequest food = mapper.readValue(foodJson, FoodRequest.class);

            food1 = service.addFood(food, file);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Json Format");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"File Type Error");
        }

        return new ResponseEntity(food1, HttpStatus.CREATED);
    }

    @GetMapping("/get-foods")
    private ResponseEntity<List<FoodResponse>>getFoods(){
        List<FoodResponse> foods = null;
        foods = service.getFoods();
        return new ResponseEntity(foods, HttpStatus.OK);
    }

    @GetMapping("/get-food/{id}")
    private ResponseEntity<FoodResponse> getFood(@PathVariable String id){
        FoodResponse food = null;
        try {
            food = service.getFood(id);
            return new ResponseEntity<>(food,HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Food not found");
        }
    }

    @GetMapping("/getFoodImage/{id}")
    private ResponseEntity<byte[]> getFoodImage(@PathVariable String id){
        byte[] image = null;
        try {
            image = service.getFoodImage(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(image,HttpStatus.OK);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Image not found");
        }
    }

    @DeleteMapping("/delete-food/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteFood(@PathVariable String id) {
        try{
            service.deleteFood(id);
        } catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Food not found by id: "+ id);
        }
    }
    @PutMapping("/update-food/{id}")
    public ResponseEntity<FoodResponse> updateFood(
            @PathVariable String id,
            @RequestPart("food") String foodJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FoodRequest food = mapper.readValue(foodJson, FoodRequest.class);
            FoodResponse foodResponse = service.updateFood(id, food, file);
            return ResponseEntity.ok(foodResponse);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found", e);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid food data", e);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while updating food: " + e.getMessage()
            );
        }
    }
}

