package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Role;
import com.buildweek.unit4javabuild.models.User;
import com.buildweek.unit4javabuild.models.UserRoles;
import com.buildweek.unit4javabuild.repository.RoleRepository;
import com.buildweek.unit4javabuild.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServicesImpl implements RoleServices
{
    @Autowired
    private RoleRepository rolerepo;

    @Autowired
    private UserRepository userrepo;

    @Override
    public List<Role> findAll()
    {
        List<Role> myList = new ArrayList<>();

        rolerepo.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }

    @Override
    public Role findByName(String name)
    {
        Role rolename = rolerepo.findByNameContainingIgnoreCase(name);

        if (rolename != null)
        {
            return rolename;
        } else
        {
            throw new EntityNotFoundException("Role name " + name + " not Found!");
        }
    }

    @Override
    public Role findRoleById(long roleid)
    {
        return rolerepo.findById(roleid)
                .orElseThrow(() -> new EntityNotFoundException("Role id " + roleid + " not Found!"));
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        Role newRole = new Role();

        if (role.getRoleid() != 0)
        {
            rolerepo.findById(role.getRoleid())
                    .orElseThrow(() -> new EntityNotFoundException("Role id " + role.getRoleid() + " not found!"));
            newRole.setRoleid(role.getRoleid());
        }

        newRole.setName(role.getName().toUpperCase());

        for (UserRoles ur : role.getUsers())
        {
            User addUser = userrepo.findById(ur.getUser().getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User id " + ur.getUser().getUserid() + "not Found!"));
            newRole.getUsers().add(new UserRoles(addUser, newRole));
        }

        return rolerepo.save(role);
    }

    @Transactional
    @Override
    public void delete(long id) {
        rolerepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
        rolerepo.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        rolerepo.deleteAll();
    }
}
