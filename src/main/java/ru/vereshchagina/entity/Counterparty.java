package ru.vereshchagina.entity;


import lombok.*;
import ru.vereshchagina.model.CounterpartyForm;

import javax.persistence.*;

/**
 * Класс, описывабщий контрагента (Связан с таблицей "counterparty")
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "counterparty")
public class Counterparty {
    /**
     * ID контрагента
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Наименование контрагента
     */
    @Column(name = "name")
    private String name;
    /**
     * ИНН контрагента
     */
    @Column(name = "inn")
    private String inn;
    /**
     * Код причины постановки на учет(КПП)
     */
    @Column(name = "kpp")
    private String kpp;
    /**
     * Номер счета контрагента
     */
    @Column(name = "account_number")
    private String accountNumber;
    /**
     * Банковский идентификационный код(БИК) контрагента
     */
    @Column(name = "bik")
    private String bik;
}

