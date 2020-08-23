package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.FoodItem;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemRepository extends CrudRepository<FoodItem, Long> {
}
