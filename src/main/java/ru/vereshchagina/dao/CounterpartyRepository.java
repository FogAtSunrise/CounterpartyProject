package ru.vereshchagina.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vereshchagina.entity.Counterparty;

import java.util.Optional;

/**
 * JPA-репозиторий
 */
@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {

    /**
     * Метод поиска контрагента по имени
     *
     * @param name имя контрагента
     * @return объект контрагента в обертке {@link Optional}
     */

    Optional<Counterparty> findByName(String name);

    /**
     * Метод поиска контрагента по БИК и номеру счета
     *
     * @param bik           БИК банка
     * @param accountNumber номер счета контрагента
     * @return объект контрагента в обертке {@link Optional}
     */

    Optional<Counterparty> findFirstByBikAndAccountNumber(String bik, String accountNumber);

    /**
     * Метод удаления контрагента по имени
     *
     * @param name имя контрагента
     */
    void deleteByName(String name);

    /**
     * Метод удаления контрагента по id
     *
     * @param id контрагента
     */
    void deleteById(Long id);

}
