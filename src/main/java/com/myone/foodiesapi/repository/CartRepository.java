package com.myone.foodiesapi.repository;

import com.myone.foodiesapi.entity.CartsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<CartsEntity, String> {
    Optional <CartsEntity> findByUserId(String userId);
    void deleteByUserId(String userId);
}
