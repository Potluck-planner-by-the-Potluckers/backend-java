package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.models.Attendee;
import com.buildweek.unit4javabuild.models.Role;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.services.AttendeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/attendees")
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

    @GetMapping(value = "/attendee/{attendeeid}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Long attendeeid) throws Exception
    {
        Attendee attendee = attendeeServices.findAttendeeById(attendeeid);
        return new ResponseEntity<>(attendee, HttpStatus.OK);
    }

    @GetMapping(value = "/attendees/name/like/{name}", produces = "application/json")
    public ResponseEntity<?> getAttendeeLikeName(@PathVariable String name)
    {
        List<Attendee> attendees = attendeeServices.findByNameContaining(name);

        return new ResponseEntity<>(attendees, HttpStatus.OK);
    }

    @PostMapping(value = "/attendee", consumes = "application/json")
    public ResponseEntity<?> addNewAttendee(@Valid @RequestBody Attendee newAttendee) throws Exception {
        newAttendee.setAttendeeid(0);
        newAttendee = attendeeServices.save(newAttendee);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newAttendeeURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/roleid")
                .buildAndExpand(newAttendee.getAttendeeid())
                .toUri();
        responseHeaders.setLocation(newAttendeeURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/attendee/{attendeeid}", consumes = "application/json")
    public ResponseEntity<?> updateFullAttendee(@Valid @RequestBody Attendee updateAttendee, @PathVariable long attendeeid) throws Exception
    {
        updateAttendee.setAttendeeid(attendeeid);
        attendeeServices.save(updateAttendee);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/attendee/{attendeeid}", consumes = "application/json")
    public ResponseEntity<?> patchAttendee(@RequestBody Attendee patchAttendee, @PathVariable long attendeeid)
    {
        attendeeServices.update(patchAttendee, attendeeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/attendee/going/{attendeeid}")
    public ResponseEntity<?> markGoing(@PathVariable long attendeeid)
    {
        attendeeServices.markGoing(attendeeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/attendee/{attendeeid}")
    public ResponseEntity<?> deleteAttendee(@PathVariable long attendeeid)
    {
        attendeeServices.delete(attendeeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
