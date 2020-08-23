package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Role;
import com.buildweek.unit4javabuild.repository.RoleRepository;
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
        Role rolename = rolerepo.findByNameIgnoringCase(name);

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

    @Override
    public Role save(Role role)
    {
        if (role.getUsers().size() > 0)
        {
            throw new EntityExistsException("User Roles are not updated through Role.");
        }

        return null;
    }
}
