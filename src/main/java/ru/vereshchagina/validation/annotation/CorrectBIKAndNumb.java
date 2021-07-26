package ru.vereshchagina.validation.annotation;

import ru.vereshchagina.validation.CorrectBIKAndNumbClass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация для валидации БИК и номер счета
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  CorrectBIKAndNumbClass.class)
@Documented
public @interface CorrectBIKAndNumb {
    String message() default "{CorrectBIKAndNumbClass.invalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
