package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.models.Potluck;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.services.PotluckServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/potlucks")
public class PotluckController
{
    @Autowired
    private PotluckServices potluckServices;

    @GetMapping(value = "/potlucks", produces = "application/json")
    public ResponseEntity<?> getAllPotlucks()
    {
        List<Potluck> myList = potluckServices.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/potluck/{potluckid}", produces = "application/json")
    public ResponseEntity<?> getPotluckById( @PathVariable Long potluckid) throws Exception
    {
        Potluck potluck = potluckServices.findPotluckById(potluckid);
        return new ResponseEntity<>(potluck, HttpStatus.OK);
    }

    @GetMapping(value = "/potlucks/name/like/{potluckname}", produces = "application/json")
    public ResponseEntity<?> getPotluckLikeName(
            @PathVariable String potluckname)
    {
        List<Potluck> potlucks = potluckServices.findByNameContaining(potluckname);
        return new ResponseEntity<>(potlucks, HttpStatus.OK);
    }

    @PostMapping(value = "/potluck",
            consumes = "application/json")
    public ResponseEntity<?> addNewPotluck(@Valid @RequestBody Potluck newPotluck) throws Exception
    {
        newPotluck.setPotluckid(0);
        newPotluck = potluckServices.save(newPotluck);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPotluckURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newPotluck.getPotluckid())
                .toUri();
        responseHeaders.setLocation(newPotluckURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/potluck/{potluckid}", consumes = "application/json")
    public ResponseEntity<?> updateFullPotluck(@Valid @RequestBody Potluck updatePotluck, @PathVariable long potluckid) throws Exception
    {
        updatePotluck.setPotluckid(potluckid);
        potluckServices.save(updatePotluck);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/potluck/{potluckid}", consumes = "application/json")
    public ResponseEntity<?> patchPotluck(@RequestBody Potluck patchPotluck, @PathVariable long potluckid)
    {
        potluckServices.update(patchPotluck, potluckid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/potluck/{potluckid}")
    public ResponseEntity<?> deletePotluck(@PathVariable long potluckid)
    {
        potluckServices.delete(potluckid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
