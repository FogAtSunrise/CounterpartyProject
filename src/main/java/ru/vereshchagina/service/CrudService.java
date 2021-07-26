package ru.vereshchagina.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vereshchagina.entity.Counterparty;
import ru.vereshchagina.exception.ResourceNotFoundException;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.repository.CounterpartyRepository;

import java.util.Optional;

/**
 * Клас сервис для работы с базой данных
 */
@Service
public class CrudService {
    @Autowired
    private CounterpartyRepository usersRepository;
    @Autowired
    private MapperFacade mapperFacade;

    /**
     * Метод сохранения данных нового контрагента
     *
     * @param counterForm сохраняемый контрагент
     */
    public void save(CounterpartyForm counterForm) {
        Counterparty inputAgent = mapperFacade.map(counterForm, Counterparty.class);
        usersRepository.save(inputAgent);
    }

    /**
     * Метод удаления данных контрагента по id
     *
     * @param id контрагента
     */
    public void deleteById(Long id) {
        try {
            usersRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Counterparty[id = " + id + "] was not find");
        }
    }

    /**
     * Метод удаления контрагента по имени (используется в тестах)
     *
     * @param name наименование контрагента
     */
    public void deleteByName(String name) {
        try {
            usersRepository.deleteByName(name);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Counterparty[name = " + name + "] was not find");
        }
    }

    /**
     * Метод обновления данных контрагента
     *
     * @param counterForm обновляемый контрагент
     */
    public void update(CounterpartyForm counterForm) {
        Optional<Counterparty> counter = usersRepository.findById(counterForm.getId());
        if (counter.isPresent()) {
            Counterparty agentDB = counter.get();
            agentDB.setName(counterForm.getName());
            agentDB.setInn(counterForm.getInn());
            agentDB.setKpp(counterForm.getKpp());
            agentDB.setAccountNumber(counterForm.getAccountNumber());
            agentDB.setBik(counterForm.getBik());
            usersRepository.save(agentDB);
        } else
            throw new ResourceNotFoundException("Counterparty[id = " + counterForm.getId() + "] was not find");
    }

}
