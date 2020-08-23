package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.services.AttendeeServices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendeeRepository extends CrudRepository<Attendee, Long>
{
//    List<Attendee> findByContainingIgnoreCase(String name);
}
