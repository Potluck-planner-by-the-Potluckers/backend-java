package com.buildweek.unit4javabuild.services;

import com.buildweek.unit4javabuild.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "attendeeService")
public class AttendeeServicesImpl implements AttendeeServices
{
    @Autowired
    private AttendeeRepository attendeerepo;
}
