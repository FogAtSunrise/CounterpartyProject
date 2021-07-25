package ru.vereshchagina.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vereshchagina.entity.Counterparty;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.service.FindService;
import ru.vereshchagina.validation.annotation.NotDuplicateName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class NotDuplicateNameClass implements ConstraintValidator<NotDuplicateName, String> {
    @Autowired
    private FindService finder;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<CounterpartyForm> allCounterparty = finder.findAll();
        List<String> names = new ArrayList<>();
        for (var agent: allCounterparty) {
            names.add(agent.getName());
        }
        if (names.contains(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("*такое наименование уже существует")
                    .addBeanNode()
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
