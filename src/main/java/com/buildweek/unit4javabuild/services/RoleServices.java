package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Role;

import java.util.List;

public interface RoleServices
{
    List<Role> findAll();

    Role findByName(String name);

    Role findRoleById(long id);

    Role save(Role role);
}
