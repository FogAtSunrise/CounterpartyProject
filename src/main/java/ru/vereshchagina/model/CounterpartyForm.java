package ru.vereshchagina.model;

import lombok.*;
import ru.vereshchagina.validation.annotation.CorrectBIKAndNumb;
import ru.vereshchagina.validation.annotation.NotDuplicateName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@CorrectBIKAndNumb
public class CounterpartyForm {
    private long id;

    @NotEmpty(message = "*укажите наименование")
    @NotDuplicateName
    private String name;

    @NotEmpty(message = "*укажите ИНН")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*ИНН должен состоять только из цифр")
   // @CorrectINN(message = "*указан неверный ИНН")
    private String inn;

    //@NotEmpty(message = "*укажите КПП")
    @Size(min = 9, max = 9, message = "*КПП должен состоять из 9 цифр")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*КПП должен состоять только из цифр")
    private String kpp;

    @Pattern(regexp = "(^[(0-9)]+)$", message = "*номер счета должен состоять только из цифр")
    private String accountNumber;


    @Size(min = 9, max = 9, message = "*БИК должен состоять из 9 цифр")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*БИК должен состоять только из цифр")
    private String bik;


}