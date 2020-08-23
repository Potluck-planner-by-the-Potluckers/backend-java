package com.buildweek.unit4javabuild.repository;

import com.buildweek.unit4javabuild.models.Potluck;
import org.springframework.data.repository.CrudRepository;

public interface PotluckRepository extends CrudRepository<Potluck, Long>
{
}
