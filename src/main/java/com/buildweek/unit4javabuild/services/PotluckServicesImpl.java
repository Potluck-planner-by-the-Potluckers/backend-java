package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.repository.PotluckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "potluckService")
public class PotluckServicesImpl implements PotluckServices
{
    @Autowired
    private PotluckRepository potluckrepo;
}
