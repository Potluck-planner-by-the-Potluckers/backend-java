package com.buildweek.unit4javabuild.controllers;

import com.buildweek.unit4javabuild.services.PotluckServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/potlucks")
public class PotluckController
{
    @Autowired
    private PotluckServices potluckServices;
}
