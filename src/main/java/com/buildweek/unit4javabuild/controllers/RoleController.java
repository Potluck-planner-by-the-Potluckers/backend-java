package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.models.Role;
import com.buildweek.unit4javabuild.services.RoleServices;
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
@RequestMapping(value = "/roles")
public class RoleController
{
    @Autowired
    private RoleServices roleServices;

    @GetMapping(value = "/roles", produces = "application/json")
    public ResponseEntity<?> getAllRoles()
    {
        List<Role> allRoles = roleServices.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    @GetMapping(value = "/role/{rolename}", produces = "application/json")
    public ResponseEntity<?> getRoleByName(@PathVariable String rolename) {
        Role role = roleServices.findByName(rolename);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping(value = "/role", consumes = "application/json")
    public ResponseEntity<?> addNewRole(@Valid @RequestBody Role newRole) {
        newRole.setRoleid(0);
        newRole = roleServices.save(newRole);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/roleid")
                .buildAndExpand(newRole.getRoleid())
                .toUri();
        responseHeaders.setLocation(newRoleURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
