package com.buildweek.unit4javabuild.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "attendeeService")
public class AttendeeServicesImpl implements AttendeeServices
{
}
