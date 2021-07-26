package ru.vereshchagina.validation;

import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.validation.annotation.CorrectBIKAndNumb;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Класс валидации БИК и номера счета
 */
public class CorrectBIKAndNumbClass implements ConstraintValidator<CorrectBIKAndNumb, CounterpartyForm> {
    @Override
    public boolean isValid(CounterpartyForm value, ConstraintValidatorContext context) {
        String bik = value.getBik();
        String accountNumber = value.getAccountNumber();
        if (accountNumber.length() != 20) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("*номер счета должен состоять из 20 знаков")
                    .addPropertyNode("accountNumber")
                    .addConstraintViolation();
            return false;
        }
        if (bik.charAt(6) == '0' && bik.charAt(7) == '0') {
            if (!checkForRKC(bik, accountNumber)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("*неверный ввод - счет отсутствует в данном РКЦ (БИК)")
                        .addPropertyNode("accountNumber")
                        .addConstraintViolation();
                return false;
            }
        } else {
            if (!checkForCreditOrg(bik, accountNumber)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("*коррсчет указан неверно")
                        .addPropertyNode("accountNumber")
                        .addConstraintViolation();

                return false;
            }
        }
        return true;
    }


    /**
     * Метод проверки номер счета, открытого в РКЦ
     *
     * @param bik           БИК
     * @param accountNumber номер счета
     * @return true - если номер счета правильный, иначе false
     */
    private boolean checkForRKC(String bik, String accountNumber) {
        accountNumber = '0' + bik.substring(4, 6) + accountNumber;
        return getControlNumber(accountNumber) == 0;
    }

    /**
     * Метод проверки номера счета, открытого в кредитной организации
     *
     * @param bik           БИК
     * @param accountNumber номер счета
     * @return true - если номер счета правильный, иначе false
     */
    private boolean checkForCreditOrg(String bik, String accountNumber) {
        accountNumber = bik.substring(6) + accountNumber;
        return getControlNumber(accountNumber) == 0;
    }

    /**
     * Метод подсчета контрольного числа
     *
     * @param accountNumber номер счета контрагента
     * @return контрольное число
     */
    private int getControlNumber(String accountNumber) {
        int[] controlNumber = {7, 1, 3};
        long sum = 0L;
        for (int i = 0; i < accountNumber.length(); i++)
            sum += (long) Character.getNumericValue(accountNumber.charAt(i)) * controlNumber[i % controlNumber.length];
        return (int) (sum % 10);
    }
}
