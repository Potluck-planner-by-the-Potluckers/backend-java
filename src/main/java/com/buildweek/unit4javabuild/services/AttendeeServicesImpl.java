package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "attendeeService")
public class AttendeeServicesImpl implements AttendeeServices
{
    @Autowired
    private AttendeeRepository attendeerepo;

    @Override
    public List<Attendee> findAll()
    {
        List<Attendee> myList = new ArrayList<>();

        attendeerepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public Attendee findAttendeeById(long id) throws EntityNotFoundException
    {
        return attendeerepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendee id " + id + " not Found!"));
    }

    @Override
    public List<Attendee> findByNameContaining(String name) {
        return null;
    }

    @Override
    public Attendee save(Attendee attendee) {
        return null;
    }

    @Override
    public Attendee update(Attendee attendee, long id) {
        return null;
    }

    @Override
    public void delete(long id)
    {
        attendeerepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendee id " + id + " not found!"));
        attendeerepo.deleteById(id);
    }
}
