package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.exceptions.ResourceNotFoundException;
import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.models.FoodItem;
import com.buildweek.unit4javabuild.models.Potluck;
import com.buildweek.unit4javabuild.repository.AttendeeRepository;
import com.buildweek.unit4javabuild.repository.FoodItemRepository;
import com.buildweek.unit4javabuild.repository.PotluckRepository;
import com.buildweek.unit4javabuild.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "potluckService")
public class PotluckServicesImpl implements PotluckServices
{
    @Autowired
    private PotluckRepository potluckrepo;

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private AttendeeRepository attendrepo;

    @Autowired
    private FoodItemRepository foodrepo;

    @Override
    public List<Potluck> findAll() {
        List<Potluck> myList = new ArrayList<>();

        potluckrepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public Potluck findPotluckById(long id) throws Exception {
        return potluckrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Potluck id " + id + " not Found!"));
    }

    @Override
    public List<Potluck> findByNameContaining(String name)
    {
        return potluckrepo.findByNameContainingIgnoreCase(name.toLowerCase());
    }

    @Transactional
    @Override
    public Potluck save(Potluck potluck)
    {
        Potluck newPotluck = new Potluck();

        if (potluck.getPotluckid() != 0)
        {
            potluckrepo.findById(potluck.getPotluckid())
                    .orElseThrow(() -> new ResourceNotFoundException("Potluck id " + potluck.getPotluckid() + " not Found!"));
            newPotluck.setPotluckid(potluck.getPotluckid());
        }

        newPotluck.setName(potluck.getName());
        newPotluck.setDescription(potluck.getDescription());
        newPotluck.setLocation(potluck.getLocation());
        newPotluck.setDate(potluck.getDate());
        newPotluck.setTime(potluck.getTime());

        if (potluck.getUser() != null)
        {
            newPotluck.setUser(userrepo.findById(potluck.getUser().getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + potluck.getUser().getUserid() + " not Found")));
            newPotluck.setUser(potluck.getUser());
        }

        newPotluck.getFooditems().clear();
        for (FoodItem fi : potluck.getFooditems())
        {
            newPotluck.getFooditems().add(new FoodItem(
                    fi.getName(),
                    fi.getType(),
                    fi.getAttendeename(),
                    newPotluck));
        }

        newPotluck.getAttendees().clear();
        for (Attendee attendee : potluck.getAttendees())
        {
            newPotluck.getAttendees().add(new Attendee(
                    attendee.getName(),
                    attendee.getUser(),
                    newPotluck));
        }

        return potluckrepo.save(newPotluck);
    }

    @Transactional
    @Override
    public Potluck update(Potluck potluck, long id) throws Exception
    {
        Potluck currentPotluck = findPotluckById(id);

        if (potluck.getName() != null)
        {
            currentPotluck.setName(potluck.getName().toLowerCase());
        }

        if (potluck.getDescription() != null)
        {
            currentPotluck.setDescription(potluck.getDescription());
        }

        if (potluck.getLocation() != null)
        {
            currentPotluck.setLocation(potluck.getLocation());
        }

        if (potluck.getDate() != null)
        {
            currentPotluck.setDate(potluck.getDate());
        }

        if (potluck.getTime() != null)
        {
            currentPotluck.setTime(potluck.getTime());
        }

        if (potluck.getUser() != null)
        {
            currentPotluck.setUser(potluck.getUser());
        }

        if (potluck.getAttendees().size() > 0)
        {
            currentPotluck.getAttendees().clear();
            for (Attendee attendee : potluck.getAttendees())
            {
                Attendee addAttendee = attendrepo.findById(attendee.getAttendeeid())
                        .orElseThrow(() -> new ResourceNotFoundException("Attendee id " + attendee.getAttendeeid() + " not Found"));
                currentPotluck.getAttendees().add(new Attendee(
                        attendee.getName(),
                        attendee.getUser(),
                        currentPotluck
                ));
            }
        }

        if (potluck.getFooditems().size() > 0)
        {
            currentPotluck.getFooditems().clear();
            for (FoodItem food : potluck.getFooditems())
            {
                FoodItem addFood = foodrepo.findById(food.getFoodid())
                        .orElseThrow(() -> new ResourceNotFoundException("Food id " + food.getFoodid() + " not Found!"));
                currentPotluck.getFooditems().add(new FoodItem(
                        food.getName(),
                        food.getType(),
                        food.getAttendeename(),
                        currentPotluck
                ));
            }
        }

        return potluckrepo.save(currentPotluck);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        potluckrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Potluck id " + id + " not found!"));
        potluckrepo.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        potluckrepo.deleteAll();
    }
}
