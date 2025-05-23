package com.myone.foodiesapi.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.myone.foodiesapi.entity.FoodEntity;

@Repository
public interface FoodRepository extends MongoRepository<FoodEntity, String> {

    FoodEntity findByName(String name);

}


//import com.myone.foodiesapi.entity.FoodEntity;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface FoodRepository extends MongoRepository<FoodEntity,String> {
//}
