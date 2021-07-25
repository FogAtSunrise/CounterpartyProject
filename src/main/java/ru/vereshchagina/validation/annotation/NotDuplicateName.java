package ru.vereshchagina.validation.annotation;

import ru.vereshchagina.validation.CorrectBIKAndNumbClass;
import ru.vereshchagina.validation.NotDuplicateNameClass;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  NotDuplicateNameClass.class)
@Documented
public @interface NotDuplicateName {
     String message() default "{NotDuplicateNameClass.invalid}";
     Class<?>[] groups() default { };
     Class<? extends Payload>[] payload() default { };

}