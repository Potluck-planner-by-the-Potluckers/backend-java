package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "userService")
public class UserServicesImpl implements UserServices
{
    @Autowired
    private RoleRepository rolerepo;
}
