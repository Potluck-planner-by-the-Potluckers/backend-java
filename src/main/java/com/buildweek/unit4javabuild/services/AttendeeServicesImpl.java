package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Attendee;
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

    @Autowired
    private UserServices userServices;

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
    public List<Attendee> findByNameContaining(String name)
    {
//        return attendeerepo.findByNameContainingIgnoreCase(name.toLowerCase());
        return null;
    }

    @Override
    public Attendee save(Attendee attendee) throws Exception {
        Attendee newAttendee = new Attendee();

        if (attendee.getAttendeeid() != 0)
        {
            attendeerepo.findById(attendee.getAttendeeid())
                    .orElseThrow(() -> new EntityNotFoundException("Attendee id " + attendee.getAttendeeid() + " not Found!"));
            newAttendee.setAttendeeid(attendee.getAttendeeid());
        }

        newAttendee.setGoing(attendee.isGoing());
        if (attendee.getUser() != null)
        {
            newAttendee.setUser(userServices.findUserById(attendee.getUser().getUserid()));
        }

        newAttendee.setPotlucks(attendee.getPotlucks());

        return attendeerepo.save(newAttendee);
    }

    @Override
    public Attendee update(Attendee attendee, long id)
    {
        Attendee updateUser = findAttendeeById(id);

        return null;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        attendeerepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendee id " + id + " not found!"));
        attendeerepo.deleteById(id);
    }
}
