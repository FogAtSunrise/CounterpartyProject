package ru.vereshchagina.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vereshchagina.entity.Counterparty;
import ru.vereshchagina.exception.ResourceNotFoundException;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.repository.CounterpartyRepository;
import ma.glasnost.orika.MapperFacade;

import java.util.List;
import java.util.Optional;


/**
 * Клас сервис для осуществления поиска по базе данных
 */
@Service
public class FindService {

    @Autowired
    private CounterpartyRepository usersRepository;

    @Autowired
    private MapperFacade mapperFacade;

    /**
     * Метод получения списка всех контрагентов в БД
     *
     * @return список контрагентов
     */
    public List<CounterpartyForm> findAll() {
        return mapperFacade.mapAsList(usersRepository.findAll(), CounterpartyForm.class);
    }


    /**
     * Метод поиска контрагента по наименованию
     *
     * @param name наименование контрагента
     * @return контрагент
     * @throws ResourceNotFoundException если контрагент с указанным наименованием не найден в БД
     */
    public CounterpartyForm findByName(String name) {

        Optional<Counterparty> counter = usersRepository.findByName(name);
        if (counter.isPresent()) {
            CounterpartyForm webAgent = mapperFacade.map(counter.get(), CounterpartyForm.class);
            return webAgent;
        } else
            throw new ResourceNotFoundException("Counterparty [name = " + name + "] was not find");
    }

    /**
     * Метод поиска контрагента по БИК и номеру счета
     *
     * @param bik           БИК контрагента
     * @param accountNumber номер счета контрагента
     * @return контрагент
     * @throws ResourceNotFoundException если не нашли контрагента по БИКу и номеру счета
     */
    public CounterpartyForm findByBikAndAccountNumber(String bik, String accountNumber) {
        Optional<Counterparty> agent = usersRepository.findFirstByBikAndAccountNumber(bik, accountNumber);
        if (agent.isPresent()) {
            CounterpartyForm webAgent = mapperFacade.map(agent.get(), CounterpartyForm.class);
            return webAgent;
        } else
            throw new ResourceNotFoundException("Counterparty [bik = " + bik + ", account = " + accountNumber +
                    "] was not find");
    }

    /**
     * Метод поиска данных контрагента по id
     *
     * @param id контрагента
     * @return искомый контрагент
     * @throws ResourceNotFoundException если контрагент с указанным id не найден в БД
     */
    public CounterpartyForm findById(Long id) {

        Optional<Counterparty> counter = usersRepository.findById(id);
        if (counter.isPresent()) {
            CounterpartyForm finderAgent = mapperFacade.map(counter.get(), CounterpartyForm.class);
            return finderAgent;
        } else
            throw new ResourceNotFoundException("Counterparty[id = " + id + "] was not find");

    }

}