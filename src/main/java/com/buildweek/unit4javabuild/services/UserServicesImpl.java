package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.exceptions.ResourceNotFoundException;
import com.buildweek.unit4javabuild.models.*;
import com.buildweek.unit4javabuild.repository.AttendeeRepository;
import com.buildweek.unit4javabuild.repository.PotluckRepository;
import com.buildweek.unit4javabuild.repository.RoleRepository;
import com.buildweek.unit4javabuild.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServicesImpl implements UserServices
{
    @Autowired
    private UserRepository userrepo;

    @Autowired
    private RoleRepository rolerepo;

    @Autowired
    private PotluckRepository potluckrepo;

    @Autowired
    private AttendeeRepository attendrepo;

    @Override
    public List<User> findAll()
    {
        List<User> myList = new ArrayList<>();

        userrepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public User findUserById(long id) throws ResourceNotFoundException
    {
        return userrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not Found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {
        return userrepo.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userrepo.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());

        if (user.getFname() != null)
        {
            newUser.setFname(user.getFname().toLowerCase());
        }
        if (user.getLname() != null)
        {
            newUser.setLname(user.getLname().toLowerCase());
        }

        newUser.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = rolerepo.findById(ur.getRole().getRoleid())
                    .orElseThrow(() -> new ResourceNotFoundException("Role id " + ur.getRole().getRoleid()));
            newUser.getRoles()
                    .add(new UserRoles(newUser, addRole));
        }

        newUser.getPotlucks().clear();
        for (Potluck pl : user.getPotlucks())
        {
            newUser.getPotlucks().add(new Potluck(
                    pl.getName(),
                    pl.getDescription(),
                    pl.getLocation(),
                    pl.getDate(),
                    pl.getTime(),
                    newUser));
        }

        newUser.getAttendees().clear();
        for (Attendee attendee : user.getAttendees())
        {
            newUser.getAttendees().add(new Attendee(
                    attendee.getName(),
                    newUser,
                    attendee.getPotluck()));
        }

        return userrepo.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long userid)
    {
        User currentUser = findUserById(userid);

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername()
                    .toLowerCase());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }

        if (user.getFname() != null)
        {
            currentUser.setFname(user.getFname()
                    .toLowerCase());
        }

        if (user.getLname() != null)
        {
            currentUser.setLname(user.getLname());
        }

        if (user.getRoles()
                .size() > 0)
        {
            currentUser.getRoles()
                    .clear();
            for (UserRoles ur : user.getRoles())
            {
                Role addRole = rolerepo.findById(ur.getRole().getRoleid())
                        .orElseThrow(() -> new ResourceNotFoundException("Role id " + ur.getRole().getRoleid()));

                currentUser.getRoles()
                        .add(new UserRoles(currentUser, addRole));
            }
        }

        if (user.getPotlucks().size() > 0)
        {
            currentUser.getPotlucks().clear();
            for (Potluck pl : user.getPotlucks())
            {
                Potluck addPotluck = potluckrepo.findById(pl.getPotluckid())
                        .orElseThrow(() -> new ResourceNotFoundException("Potluck id " + pl.getPotluckid() + " not Found!"));
                currentUser.getPotlucks().add(new Potluck(
                        pl.getName(),
                        pl.getDescription(),
                        pl.getLocation(),
                        pl.getDate(),
                        pl.getTime(),
                        currentUser
                ));
            }
        }

        if (user.getAttendees().size() > 0)
        {
            currentUser.getAttendees().clear();
            for (Attendee attendee : user.getAttendees())
            {
                Attendee addAttendee = attendrepo.findById(attendee.getAttendeeid())
                        .orElseThrow(() -> new ResourceNotFoundException("Attendee id " + attendee.getAttendeeid() + " not Found!"));
                currentUser.getAttendees().add(new Attendee(
                         attendee.getName(),
                         currentUser,
                         attendee.getPotluck()
                ));
            }
        }

        return userrepo.save(currentUser);
    }

    @Transactional
    @Override
    public void delete(long userid)
    {
        userrepo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        userrepo.deleteById(userid);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userrepo.deleteAll();
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepo.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }
}
