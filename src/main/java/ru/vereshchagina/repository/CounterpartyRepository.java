package ru.vereshchagina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vereshchagina.entity.Counterparty;

import java.util.List;
import java.util.Optional;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {

   Optional<Counterparty> findByName(String name);

    Optional<Counterparty> findByBikAndAccountNumber(String bik, String accountNumber);
    void deleteByName(String name);
    void deleteById(Long id);

}
