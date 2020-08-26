package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.exceptions.ResourceNotFoundException;
import com.buildweek.unit4javabuild.models.FoodItem;
import com.buildweek.unit4javabuild.repository.FoodItemRepository;
import com.buildweek.unit4javabuild.repository.PotluckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "foodItemService")
public class FoodItemServicesImpl implements FoodItemServices
{
    @Autowired
    private FoodItemRepository fooditemrepo;

    @Autowired
    private PotluckRepository potluckrepo;

    @Override
    public List<FoodItem> findAll()
    {
        List<FoodItem> myList = new ArrayList<>();

        fooditemrepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public FoodItem findFoodById(long id) throws ResourceNotFoundException {
        return fooditemrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food Item id " + id + " not Found!"));
    }

    @Override
    public List<FoodItem> findByNameContaining(String name)
    {
        return fooditemrepo.findByNameContainingIgnoreCase(name.toLowerCase());
    }

    @Override
    public FoodItem save(FoodItem foodItem) throws ResourceNotFoundException {
        FoodItem newFood = new FoodItem();

        if (foodItem.getFoodid() != 0)
        {
            fooditemrepo.findById(foodItem.getFoodid())
                    .orElseThrow(() -> new ResourceNotFoundException("Food item " + foodItem.getFoodid() + " not Found!"));
            newFood.setFoodid(foodItem.getFoodid());
        }

        newFood.setName(foodItem.getName());
        newFood.setType(foodItem.getType());
        newFood.setAttendeename(foodItem.getAttendeename());

        if (foodItem.getPotluck() != null)
        {
            newFood.setPotluck(potluckrepo.findById(foodItem.getPotluck().getPotluckid())
                    .orElseThrow(() -> new ResourceNotFoundException("Potluck " + foodItem.getPotluck().getPotluckid() + " not Found!")));
        }

        return fooditemrepo.save(newFood);
    }

    @Override
    public FoodItem update(FoodItem foodItem, long id)
    {

        FoodItem currentFood = findFoodById(id);

        if (foodItem.getName() != null)
        {
            currentFood.setName(foodItem.getName().toLowerCase());
        }

        if (foodItem.getType() != null)
        {
            currentFood.setType(foodItem.getType());
        }

        if (foodItem.getAttendeename() != null)
        {
            currentFood.setAttendeename(foodItem.getAttendeename());
        }

        if (foodItem.getPotluck() != null)
        {
            currentFood.setPotluck(foodItem.getPotluck());
        }

        return fooditemrepo.save(currentFood);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        fooditemrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food Item id " + id + " not found!"));
        fooditemrepo.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        fooditemrepo.deleteAll();
    }
}