package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.FoodItem;
import com.buildweek.unit4javabuild.models.Potluck;

import java.util.List;

public interface FoodItemServices
{
    List<FoodItem> findAll();

    FoodItem findFoodById(long id) throws Exception;

    List<FoodItem> findByNameContaining(String name);

    FoodItem save(FoodItem foodItem) throws Exception;

    FoodItem update(FoodItem foodItem, long id);

    void delete(long id);

    void deleteAll();
}
