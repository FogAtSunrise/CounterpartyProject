package ru.vereshchagina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vereshchagina.entity.Counterparty;

import java.util.List;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
    void deleteByName(String name);
    Counterparty findByName(String name);
    Counterparty findByBikAndAccountNumber(String bik, String accountNumber);
    //List<Counterparty> findByBikAndAccountNumber(String bik, String accountNumber);
    List<Counterparty> findFirstByName(String name);
    //Counterparty findFirstByName(String name);
}
