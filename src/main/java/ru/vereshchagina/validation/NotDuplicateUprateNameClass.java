package ru.vereshchagina.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.service.FindService;

import ru.vereshchagina.validation.annotation.NotDuplicateUprateName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
/**
 * Класс валидации поля наименования контрагента при изменении
 */
public class NotDuplicateUprateNameClass  implements ConstraintValidator<NotDuplicateUprateName, CounterpartyForm> {
    @Autowired
    private FindService finder;

    /**
     * переопределенный метод, сначала осуществляет поиск по индексу в базе, если элемент новый, то поиск
     * выбросит исключение, в таком случае просто если же элемент уже есть и мы его просто изменяем, тогда нужно сравнить со своей старой версией,
     * чтобы понять, менялось ли имя, если менялось, только тогда проверять на уникальность
     */
    @Override
    public boolean isValid(CounterpartyForm value, ConstraintValidatorContext context) {
        String oldName = null;
        String newName = value.getName();
        try {
            oldName = finder.findById(value.getId()).getName();
        } catch (Exception e) {
            oldName = newName+"another";
        } finally {

            if (oldName.equalsIgnoreCase(newName)) {
                return true;
            }
            List<CounterpartyForm> allCounterparty = finder.findAll();
            List<String> names = new ArrayList<>();
            for (var agent : allCounterparty) {
                names.add(agent.getName());
            }
            if (names.contains(newName)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("*такое наименование уже существует")
                        .addBeanNode()
                        .addConstraintViolation();
                return false;
            }
            return true;
        }
    }

}