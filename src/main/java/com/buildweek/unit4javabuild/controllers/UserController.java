package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.services.UserServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserServices userServices;

    // localhost:2019

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> getAllUsers()
    {
        List<User> myList = userServices.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userid}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Long userid) throws Exception
    {
        User user = userServices.findUserById(userid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users/name/like/{username}", produces = "application/json")
    public ResponseEntity<?> getUserLikeName(@PathVariable String username)
    {
        List<User> users = userServices.findByNameContaining(username);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{userid}", consumes = "application/json")
    public ResponseEntity<?> updateFullUser(@Valid @RequestBody User updateUser, @PathVariable long userid)
    {
        updateUser.setUserid(userid);
        userServices.save(updateUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/user/{userid}", consumes = "application/json")
    public ResponseEntity<?> patchUser(@RequestBody User patchUser, @PathVariable long userid)
    {
        userServices.update(patchUser, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid)
    {
        userServices.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "returns the currently authenticated user",
            response = User.class)
    @GetMapping(value = "/getuserinfo",
            produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication)
    {
        User u = userServices.findByName(authentication.getName());
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }
}
