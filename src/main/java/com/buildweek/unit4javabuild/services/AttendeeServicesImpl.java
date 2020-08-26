package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.exceptions.ResourceNotFoundException;
import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.repository.AttendeeRepository;
import com.buildweek.unit4javabuild.repository.PotluckRepository;
import com.buildweek.unit4javabuild.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "attendeeService")
public class AttendeeServicesImpl implements AttendeeServices
{
    @Autowired
    private AttendeeRepository attendeerepo;

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private PotluckRepository potluckrepo;

    @Override
    public List<Attendee> findAll()
    {
        List<Attendee> myList = new ArrayList<>();

        attendeerepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public Attendee findAttendeeById(long id) throws ResourceNotFoundException
    {
        return attendeerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee id " + id + " not Found!"));
    }

    @Override
    public List<Attendee> findByNameContaining(String name)
    {
        return attendeerepo.findByNameContainingIgnoreCase(name.toLowerCase());
    }

    @Override
    public Attendee save(Attendee attendee) throws Exception {
        Attendee newAttendee = new Attendee();

        if (attendee.getAttendeeid() != 0)
        {
            attendeerepo.findById(attendee.getAttendeeid())
                    .orElseThrow(() -> new ResourceNotFoundException("Attendee id " + attendee.getAttendeeid() + " not Found!"));
            newAttendee.setAttendeeid(attendee.getAttendeeid());
        }

        newAttendee.setName(attendee.getName());

        newAttendee.setGoing(attendee.isGoing());

        if (attendee.getUser() != null)
        {
            newAttendee.setUser(userrepo.findById(attendee.getUser().getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + attendee.getUser().getUserid() + " not Found!")));
        }

        newAttendee.setPotluck(potluckrepo.findById(attendee.getPotluck().getPotluckid())
                    .orElseThrow(() -> new ResourceNotFoundException("Potluck id " + attendee.getPotluck().getPotluckid() + " not Found!")));

        return attendeerepo.save(newAttendee);
    }

    @Override
    public Attendee update(Attendee attendee, long id)
    {
        Attendee currentAttendee = findAttendeeById(id);

        if (attendee.getName() != null)
        {
            currentAttendee.setName(attendee.getName().toLowerCase());
        }

        if (attendee.getUser() != null)
        {
            currentAttendee.setUser(attendee.getUser());
        }

        if (attendee.getPotluck() != null)
        {
            currentAttendee.setPotluck(attendee.getPotluck());
        }

        return attendeerepo.save(currentAttendee);
    }

    @Override
    public void markGoing(long id) {
        Attendee isGoing = attendeerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee id " + id + " not Found"));

        isGoing.setGoing(!isGoing.isGoing());

        attendeerepo.save(isGoing);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        attendeerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee id " + id + " not found!"));
        attendeerepo.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        attendeerepo.deleteAll();
    }
}
