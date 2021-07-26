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

@Service

public class FindService {

    @Autowired
    private CounterpartyRepository usersRepository;

    @Autowired
    private MapperFacade mapperFacade;

    public List<CounterpartyForm> findAll() {
        return mapperFacade.mapAsList(usersRepository.findAll(), CounterpartyForm.class);
    }

    public CounterpartyForm findByName(String name) {

        Optional<Counterparty> agent = usersRepository.findByName(name);
        //Counterparty agent = usersRepository.findByName(name);
        if (agent.isPresent()) {
        CounterpartyForm webAgent = mapperFacade.map(agent.get(), CounterpartyForm.class);
        return webAgent;
        }
        else
            throw new ResourceNotFoundException("Counterparty [name = " + name + "] was not find");
    }


    public CounterpartyForm findByBikAndAccountNumber(String bik, String numberAccount) {
        Optional<Counterparty> agent = usersRepository.findByBikAndAccountNumber(bik, numberAccount);
        if (agent.isPresent()) {
            CounterpartyForm webAgent = mapperFacade.map(agent.get(), CounterpartyForm.class);
        return webAgent;
        }
        else
            throw new ResourceNotFoundException("Counterparty [bik = " + bik + ", account = " + numberAccount +
                    "] was not find");
    }

    public CounterpartyForm findById(Long id) {

        Optional<Counterparty> agentDAO = usersRepository.findById(id);
        if (agentDAO.isPresent()) {
            CounterpartyForm finderAgent = mapperFacade.map(agentDAO.get(), CounterpartyForm.class);
            return finderAgent;
        }
        else
            throw new ResourceNotFoundException("Counterparty[id = " + id + "] was not find");

    }

}