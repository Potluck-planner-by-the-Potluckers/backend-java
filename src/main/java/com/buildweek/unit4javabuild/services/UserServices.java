package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.User;

import java.util.List;

public interface UserServices
{
    List<User> findAll();

    User findUserById(long userid) throws Exception;

    List<User> findByNameContaining(String username);

    User save(User user);

    User update(User user, long userid);

    void delete(long id);
}
