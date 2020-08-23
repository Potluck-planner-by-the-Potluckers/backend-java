package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.FoodItem;
import com.buildweek.unit4javabuild.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "foodItemService")
public class FoodItemServicesImpl implements FoodItemServices
{
    @Autowired
    private FoodItemRepository fooditemrepo;

    @Override
    public List<FoodItem> findAll()
    {
        List<FoodItem> myList = new ArrayList<>();

        fooditemrepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public FoodItem findUserById(long id) throws Exception {
        return fooditemrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food Item id " + id + " not Found!"));
    }

    @Override
    public List<FoodItem> findByNameContaining(String username) {
        return null;
    }

    @Override
    public FoodItem save(FoodItem foodItem) {
        return null;
    }

    @Override
    public FoodItem update(FoodItem foodItem, long id) {
        return null;
    }

    @Override
    public void delete(long id)
    {
        fooditemrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food Item id " + id + " not found!"));
        fooditemrepo.deleteById(id);
    }
}