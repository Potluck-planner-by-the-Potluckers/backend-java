package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.FoodItem;
import com.buildweek.unit4javabuild.models.Potluck;

import java.util.List;

public interface FoodItemServices
{
    List<FoodItem> findAll();

    FoodItem findUserById(long id) throws Exception;

    List<FoodItem> findByNameContaining(String username);

    FoodItem save(FoodItem foodItem);

    FoodItem update(FoodItem foodItem, long id);

    void delete(long id);
}
