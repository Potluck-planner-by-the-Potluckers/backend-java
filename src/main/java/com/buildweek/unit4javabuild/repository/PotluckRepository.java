package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.Potluck;
import com.buildweek.unit4javabuild.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PotluckRepository extends CrudRepository<Potluck, Long>
{
    List<Potluck> findByNameContainingIgnoreCase(String name);
}
