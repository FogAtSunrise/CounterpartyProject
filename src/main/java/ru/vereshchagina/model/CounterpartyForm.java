package ru.vereshchagina.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.vereshchagina.validation.annotation.CorrectBIKAndNumb;
import ru.vereshchagina.validation.annotation.NotDuplicateName;
import ru.vereshchagina.validation.group.OnCreate;
import ru.vereshchagina.validation.group.OnUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@CorrectBIKAndNumb
@ApiModel(value = "Модель контрагента")
public class CounterpartyForm {
    @ApiModelProperty(
            value = "Идентификатор",
            name = "id",
            dataType = "Long",
            example = "9999")
    private Long id;

    @ApiModelProperty(
            value = "Имя контрагента",
            name = "name",
            dataType = "String",
            example = "CounterAgentName")
    @NotEmpty(message = "*укажите наименование")
    @NotDuplicateName(groups = {OnUpdate.class})
    private String name;

    @ApiModelProperty(
            value = "ИНН контрагента",
            name = "inn",
            dataType = "String",
            example = "1234567890")
    @NotEmpty(message = "*укажите ИНН")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*ИНН должен состоять только из цифр")
   // @CorrectINN(message = "*указан неверный ИНН")
    private String inn;


    @ApiModelProperty(
            value = "Код причины постановки на учет (КПП)",
            name = "kpp",
            dataType = "String",
            example = "222443001")
    //@NotEmpty(message = "*укажите КПП")
    @Size(min = 9, max = 9, message = "*КПП должен состоять из 9 цифр")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*КПП должен состоять только из цифр")
    private String kpp;

    @ApiModelProperty(
            value = "Номер счета",
            name = "accountNumber",
            dataType = "String",
            example = "30101810200000000604")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*номер счета должен состоять только из цифр")
    private String accountNumber;

    @ApiModelProperty(
            value = "Банковский идентификационный код",
            name = "bik",
            dataType = "String",
            example = "040173604")
    @Size(min = 9, max = 9, message = "*БИК должен состоять из 9 цифр")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*БИК должен состоять только из цифр")
    private String bik;


}