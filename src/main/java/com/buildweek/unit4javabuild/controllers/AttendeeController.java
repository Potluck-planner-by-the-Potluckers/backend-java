package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.services.AttendeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/attendee")
public class AttendeeController
{
    @Autowired
    private AttendeeServices attendeeServices;

    @GetMapping(value = "/attendees", produces = "application/json")
    public ResponseEntity<?> getAllAttendees()
    {
        List<Attendee> myList = attendeeServices.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{attendeeid}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Long attendeeid) throws Exception
    {
        Attendee attendee = attendeeServices.findAttendeeById(attendeeid);
        return new ResponseEntity<>(attendee, HttpStatus.OK);
    }

    @GetMapping(value = "/users/name/like/{name}", produces = "application/json")
    public ResponseEntity<?> getAttendeeLikeName(@PathVariable String name)
    {
        List<Attendee> attendees = attendeeServices.findByNameContaining(name);

        return new ResponseEntity<>(attendees, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{attendeeid}", consumes = "application/json")
    public ResponseEntity<?> updateFullAttendee(@Valid @RequestBody Attendee updateAttendee, @PathVariable long attendeeid)
    {
        updateAttendee.setAttendeeid(attendeeid);
        attendeeServices.save(updateAttendee);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/user/{userid}", consumes = "application/json")
    public ResponseEntity<?> patchAttendee(@RequestBody Attendee patchAttendee, @PathVariable long attendeeid)
    {
        attendeeServices.update(patchAttendee, attendeeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteAttendee(@PathVariable long attendeeid)
    {
        attendeeServices.delete(attendeeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
