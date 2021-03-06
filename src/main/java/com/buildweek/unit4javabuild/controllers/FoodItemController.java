package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.models.FoodItem;
import com.buildweek.unit4javabuild.services.FoodItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        FoodItem foodItem = foodItemServices.findFoodById(foodid);
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }

    @GetMapping(value = "/fooditems/name/like/{name}", produces = "application/json")
    public ResponseEntity<?> getFoodLikeName(@PathVariable String name)
    {
        List<FoodItem> foodItems = foodItemServices.findByNameContaining(name);

        return new ResponseEntity<>(foodItems, HttpStatus.OK);
    }

    @PostMapping(value = "/fooditem", consumes = "application/json")
    public ResponseEntity<?> addNewRole(@Valid @RequestBody FoodItem newFood) throws Exception {
        newFood.setFoodid(0);
        newFood = foodItemServices.save(newFood);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newFoodItemURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/roleid")
                .buildAndExpand(newFood.getFoodid())
                .toUri();
        responseHeaders.setLocation(newFoodItemURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/fooditem/{foodid}", consumes = "application/json")
    public ResponseEntity<?> updateFullFood(@Valid @RequestBody FoodItem updateFoodItem, @PathVariable long foodid) throws Exception {
        updateFoodItem.setFoodid(foodid);
        foodItemServices.save(updateFoodItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/fooditem/{foodid}", consumes = "application/json")
    public ResponseEntity<?> patchFood(@RequestBody FoodItem patchFoodItem, @PathVariable long foodid)
    {
        foodItemServices.update(patchFoodItem, foodid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/fooditem/{foodid}")
    public ResponseEntity<?> deleteFood(@PathVariable long foodid)
    {
        foodItemServices.delete(foodid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
