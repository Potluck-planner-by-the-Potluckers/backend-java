package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.FoodItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodItemRepository extends CrudRepository<FoodItem, Long>
{


    List<FoodItem> findByNameContainingIgnoreCase(String name);
}
