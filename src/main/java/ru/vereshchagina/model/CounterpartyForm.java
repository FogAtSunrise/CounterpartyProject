package ru.vereshchagina.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.vereshchagina.validation.annotation.CorrectBIKAndNumb;
import ru.vereshchagina.validation.annotation.NotDuplicateUprateName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Модель сущности контрагента с валидацией полей
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@CorrectBIKAndNumb
@ApiModel(value = "Модель контрагента")
@NotDuplicateUprateName(message ="*такое наименование уже существует")
public class CounterpartyForm {
    @ApiModelProperty(
            value = "Идентификатор",
            name = "id",
            dataType = "Long",
            example = "55555")
    private Long id;

    @ApiModelProperty(
            value = "Наименование",
            name = "name",
            dataType = "String",
            example = "CounterName")
    @NotEmpty(message = "*укажите наименование")
    private String name;

    @ApiModelProperty(
            value = "ИНН",
            name = "inn",
            dataType = "String",
            example = "500100732259")
    @NotEmpty(message = "*укажите ИНН")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*ИНН должен состоять только из цифр")
    // @CorrectINN(message = "*указан неверный ИНН")
    private String inn;


    @ApiModelProperty(
            value = "Код причины постановки на учет (КПП)",
            name = "kpp",
            dataType = "String",
            example = "222443001")

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
            value = "Банковский идентификационный код (БИК)",
            name = "bik",
            dataType = "String",
            example = "040173604")
    @Size(min = 9, max = 9, message = "*БИК должен состоять из 9 цифр")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "*БИК должен состоять только из цифр")
    private String bik;


}