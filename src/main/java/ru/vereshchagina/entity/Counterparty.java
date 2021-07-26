package ru.vereshchagina.entity;


import lombok.*;
import ru.vereshchagina.model.CounterpartyForm;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "counterparty")
public class Counterparty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "inn")
    private String inn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "bik")
    private String bik;
    public Counterparty(String name, String inn, String kpp, String accountNumber, String bik)
    {
        this.name = name;
        this.inn = inn;
        this.kpp = kpp;
        this.accountNumber = accountNumber;
        this.bik = bik;
    }

    public static Counterparty from(CounterpartyForm form) {
        return Counterparty.builder()
                .name(form.getName())
                .inn(form.getInn())
                .kpp(form.getKpp())
                .accountNumber(form.getAccountNumber())
                .bik(form.getBik())
                .build();
    }
}

