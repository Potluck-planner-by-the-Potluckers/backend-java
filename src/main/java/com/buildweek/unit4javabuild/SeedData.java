package com.buildweek.unit4javabuild;

import com.buildweek.unit4javabuild.models.*;
import com.buildweek.unit4javabuild.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    UserServices userServices;

    @Autowired
    RoleServices roleServices;

    @Autowired
    AttendeeServices attendeeServices;

    @Autowired
    PotluckServices potluckServices;

    @Autowired
    FoodItemServices foodItemServices;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        userServices.deleteAll();
        roleServices.deleteAll();
        attendeeServices.deleteAll();
        potluckServices.deleteAll();
        foodItemServices.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleServices.save(r1);
        r2 = roleServices.save(r2);

        User u1 = new User("admin",
                           "$2a$10$UAR5j1M2OKq07B8sUeLiSujNcwRdpMzaSU0/glPVHouigiICETtPy",
                             "fname",
                             "lname");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));
        u1 = userServices.save(u1);

        User u2 = new User("user",
                "$2a$10$UAR5j1M2OKq07B8sUeLiSujNcwRdpMzaSU0/glPVHouigiICETtPy");
        u2.getRoles().add(new UserRoles(u2, r2));
        u2 = userServices.save(u2);

        User u3 = new User("sage",
                "$2a$10$UAR5j1M2OKq07B8sUeLiSujNcwRdpMzaSU0/glPVHouigiICETtPy");
        u3.getRoles().add(new UserRoles(u3, r2));
        u3 = userServices.save(u3);

        User u4 = new User("viriditymoon",
                "$2a$10$UAR5j1M2OKq07B8sUeLiSujNcwRdpMzaSU0/glPVHouigiICETtPy",
                "noah",
                "green");
        u4.getRoles().add(new UserRoles(u4, r2));
        u4 = userServices.save(u4);

        /**
         * Potluck Data
         */

        Potluck p1 = new Potluck("Admin's Potluck",
                "This is the description",
                "Mars",
                "1999-04-26",
                "Noon",
                u1);
        p1.getAttendees().add(new Attendee("admin", u1, p1));
        p1.getFooditems().add(new FoodItem("Pizza",
                "Entree",
                "Noah",
                p1));
        p1.getFooditems().add(new FoodItem("Cake",
                        "Dessert",
                        "Ang",
                        p1));
        potluckServices.save(p1);

        Potluck p2 = new Potluck("User's Potluck",
                "This is the description",
                "Jupiter",
                "1999-04-26",
                "Noon",
                u2);
            p2.getAttendees().add(new Attendee("noah", u2, p2));
            p2.getFooditems().add(new FoodItem("Egg Salad",
                "Side",
                "Susan",
                p2));
            p2.getFooditems().add(new FoodItem("Deviled Eggs",
                        "Side",
                        "John",
                        p2));
        potluckServices.save(p2);

        Potluck p3 = new Potluck("Sage's Potluck",
                "This is the description",
                "Earth",
                "1999-04-26",
                "Noon",
                u3);
        p3.getAttendees().add(new Attendee("john", u3, p3));
        p3.getFooditems().add(new FoodItem("Chicken",
                "Entree",
                "Kyle",
                p3));
        p3.getFooditems().add(new FoodItem("Bacon",
                        "Entree",
                        "Mia",
                        p3));
        potluckServices.save(p3);

        Potluck p4 = new Potluck("Noah's Potluck",
                "This is the description",
                "Venus",
                "1999-04-26",
                "Noon",
                u4);
        p4.getAttendees().add(new Attendee("johnny", u4, p4));
        p4.getFooditems().add(new FoodItem("Doritos",
                        "Snack",
                        "Sage",
                        p4));
        potluckServices.save(p4);
    }
}
