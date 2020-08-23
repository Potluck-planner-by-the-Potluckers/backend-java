package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "roleService")
public class RoleServicesImpl implements RoleServices
{
    @Autowired
    private UserRepository userrepo;
}
