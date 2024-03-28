package com.rosivaldolucas.votingsystemback.api.validation.uniquevalue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

  private Class<?> domainClazz;
  private String domainAttribute;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void initialize(final UniqueValue constraintAnnotation) {
    this.domainClazz = constraintAnnotation.domainClass();
    this.domainAttribute = constraintAnnotation.fieldName();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    final Query query = this.entityManager.createQuery("SELECT 1 FROM " + this.domainClazz.getName() + " WHERE " + this.domainAttribute + " = :value");
    query.setParameter("value", value);

    final List<?> list = query.getResultList();

    final boolean isListNotEmpty = !list.isEmpty();

    if (isListNotEmpty) {
      context.disableDefaultConstraintViolation();

      final String message = String
              .format("já existe um registro com o mesmo valor para o atributo %s: '%s'. campo único no sistema", this.domainAttribute, value);

      context
              .buildConstraintViolationWithTemplate(message)
              .addConstraintViolation();

      return false;
    }

    return true;
  }

}
