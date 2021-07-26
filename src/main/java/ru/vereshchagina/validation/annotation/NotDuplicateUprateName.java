package ru.vereshchagina.validation.annotation;


import ru.vereshchagina.validation.NotDuplicateUprateNameClass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация для проверки уникальности наименования при обновлении
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  NotDuplicateUprateNameClass.class)
@Documented
public @interface NotDuplicateUprateName {
    String message() default "{NotDuplicateUprateNameClass.invalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
