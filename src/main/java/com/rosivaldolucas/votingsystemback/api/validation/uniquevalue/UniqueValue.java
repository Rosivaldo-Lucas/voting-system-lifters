package com.rosivaldolucas.votingsystemback.api.validation.uniquevalue;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { UniqueValueValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

  String message() default "{com.rosivaldolucas.voting-system-backend.beanvalidation.uniquevalue}";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  Class<?> domainClass();

  String fieldName();

}
