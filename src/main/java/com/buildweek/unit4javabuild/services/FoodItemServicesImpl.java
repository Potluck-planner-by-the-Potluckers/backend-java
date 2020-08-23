package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "foodItemService")
public class FoodItemServicesImpl implements FoodItemServices
{
    @Autowired
    private FoodItemRepository fooditemrepo;
}