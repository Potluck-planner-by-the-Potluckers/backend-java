package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.models.FoodItem;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.services.FoodItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/fooditems")
public class FoodItemController
{
    @Autowired
    private FoodItemServices foodItemServices;

    @GetMapping(value = "/fooditems", produces = "application/json")
    public ResponseEntity<?> getAllFoodItems()
    {
        List<FoodItem> myList = foodItemServices.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/fooditem/{foodid}", produces = "application/json")
    public ResponseEntity<?> getFoodById(@PathVariable Long foodid) throws Exception
    {
        FoodItem foodItem = foodItemServices.findUserById(foodid);
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }

    @GetMapping(value = "/fooditems/name/like/{name}", produces = "application/json")
    public ResponseEntity<?> getFoodLikeName(@PathVariable String name)
    {
        List<FoodItem> foodItems = foodItemServices.findByNameContaining(name);

        return new ResponseEntity<>(foodItems, HttpStatus.OK);
    }

    @PutMapping(value = "/fooditem/{foodid}", consumes = "application/json")
    public ResponseEntity<?> updateFullFood(@Valid @RequestBody FoodItem updateFoodItem, @PathVariable long foodid)
    {
        updateFoodItem.setFoodid(foodid);
        foodItemServices.save(updateFoodItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/user/{foodid}", consumes = "application/json")
    public ResponseEntity<?> patchFood(@RequestBody FoodItem patchFoodItem, @PathVariable long foodid)
    {
        foodItemServices.update(patchFoodItem, foodid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{foodid}")
    public ResponseEntity<?> deleteFood(@PathVariable long foodid)
    {
        foodItemServices.delete(foodid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
