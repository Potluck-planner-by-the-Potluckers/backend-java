package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{
    List<User> findByUsernameContainingIgnoreCase(String name);
}
