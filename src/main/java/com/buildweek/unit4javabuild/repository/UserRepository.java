package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
