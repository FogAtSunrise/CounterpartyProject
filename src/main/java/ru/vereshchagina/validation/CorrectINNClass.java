package ru.vereshchagina.validation;

import ru.vereshchagina.validation.annotation.CorrectINN;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CorrectINNClass implements ConstraintValidator<CorrectINN, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.length() < 10 || value.length() == 11) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("*ИНН должен состоять из 10 или 12 цифр")
                    .addBeanNode()
                    .addConstraintViolation();
            return false;
        }
        try {
            Long.parseLong(value);
        }
        catch (Exception e) {
            return false;
        }
        if (!(checkINN10Length(value) || checkINN12Length(value))) {
            return false;
        }
        return true;
    }

    private boolean checkINN10Length(String inn) {
        if (inn.length() != 10)
            return false;
        long controlNumber = getControlNumber(new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8, 0}, inn, 10);
        return controlNumber == Character.getNumericValue(inn.charAt(inn.length() - 1));
    }


    private boolean checkINN12Length(String inn) {
        if (inn.length() != 12)
            return false;
        long firstNumber = getControlNumber(new int[]{7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0}, inn, 11);
        long secondNumber = getControlNumber(new int[]{3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0}, inn, 12);
        return (long) Character.getNumericValue(inn.charAt(10)) == firstNumber && (long) Character.getNumericValue(inn.charAt(11)) == secondNumber;
    }

    private long getControlNumber(int[] weightCoef, String inn, int count)
    {
        long sum = 0L;
        for (int i = 0; i < count; i++)
            sum += (long) weightCoef[i] * Character.getNumericValue(inn.charAt(i));
        return (sum % 11) % 10;
    }
}

