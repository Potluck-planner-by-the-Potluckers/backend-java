package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.services.AttendeeServices;
import org.springframework.data.repository.CrudRepository;

public interface AttendeeRepository extends CrudRepository<Attendee, Long>
{
}
