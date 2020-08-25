package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.models.Potluck;
import com.buildweek.unit4javabuild.models.User;

import java.util.List;

public interface PotluckServices
{
    List<Potluck> findAll();

    Potluck findPotluckById(long id) throws Exception;

    List<Potluck> findByNameContaining(String username);

    Potluck save(Potluck potluck) throws Exception;

    Potluck update(Potluck potluck, long id) throws Exception;

    void delete(long id);

    void deleteAll();
}
