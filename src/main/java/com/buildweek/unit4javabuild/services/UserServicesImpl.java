package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Role;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.models.UserRoles;
import com.buildweek.unit4javabuild.repository.RoleRepository;
import com.buildweek.unit4javabuild.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServicesImpl implements UserServices
{
    @Autowired
    private UserRepository userrepo;

    @Autowired
    private RoleServices roleServices;

    @Override
    public List<User> findAll()
    {
        List<User> myList = new ArrayList<>();

        userrepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public User findUserById(long id) throws EntityNotFoundException
    {
        return userrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not Found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {
        return userrepo.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userrepo.findById(user.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setFname(user.getFname().toLowerCase());
        newUser.setFname(user.getLname().toLowerCase());

        newUser.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleServices.findRoleById(ur.getRole()
                    .getRoleid());
            newUser.getRoles()
                    .add(new UserRoles(newUser, addRole));
        }
        return userrepo.save(newUser);
    }

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
                Role addRole = roleServices.findRoleById(ur.getRole()
                        .getRoleid());

                currentUser.getRoles()
                        .add(new UserRoles(currentUser, addRole));
            }
        }

        return userrepo.save(currentUser);
    }

    @Transactional
    @Override
    public void delete(long userid)
    {
        userrepo.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException("User id " + userid + " not found!"));
        userrepo.deleteById(userid);
    }
}
