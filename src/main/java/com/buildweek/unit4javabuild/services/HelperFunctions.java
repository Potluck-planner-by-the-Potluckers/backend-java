package com.buildweek.unit4javabuild.services;


import com.buildweek.unit4javabuild.models.ValidationError;

import java.util.List;

public interface HelperFunctions {
    List<ValidationError> getConstraintViolation(Throwable cause);
}
