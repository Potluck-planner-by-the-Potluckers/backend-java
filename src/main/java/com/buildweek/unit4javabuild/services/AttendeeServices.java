package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.models.FoodItem;

import java.util.List;

public interface AttendeeServices
{
    List<Attendee> findAll();

    Attendee findAttendeeById(long id) throws Exception;

    List<Attendee> findByNameContaining(String name);

    Attendee save(Attendee attendee) throws Exception;

    Attendee update(Attendee attendee, long id);

    void delete(long id);
}
