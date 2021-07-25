package ru.vereshchagina.validation.annotation;

import ru.vereshchagina.validation.CorrectINNClass;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  CorrectINNClass.class)
@Documented
public @interface  CorrectINN  {
    String message() default "{CorrectINNClass.invalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
