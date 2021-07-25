package ru.vereshchagina.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vereshchagina.entity.Counterparty;
import ru.vereshchagina.exception.ResourceNotFoundException;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.repository.CounterpartyRepository;

import java.util.Optional;

@Service
@Slf4j
public class CrudService {
    @Autowired
    private CounterpartyRepository usersRepository;

//    public void save(Counterparty user) {
//        usersRepository.save(user);
//    }
    public void save(CounterpartyForm counterForm) {
        Counterparty inputAgent =  Counterparty.from(counterForm);
        usersRepository.save(inputAgent);
    }


    public void deleteById(Long id) {
        try {usersRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Counterparty[id = " + id + "] could not find");
        }
    }


    public void update(CounterpartyForm agent) {
        Optional<Counterparty> agentDBO = usersRepository.findById(agent.getId());
        if (agentDBO.isPresent()) {
            Counterparty agentDB = agentDBO.get();
            agentDB.setName(agent.getName());
            agentDB.setInn(agent.getInn());
            agentDB.setKpp(agent.getKpp());
            agentDB.setAccountNumber(agent.getAccountNumber());
            agentDB.setBik(agent.getBik());
            usersRepository.save(agentDB);
        }
        else
            throw new ResourceNotFoundException("Counterparty[id = " + agent.getId() + "] could not find");
    }

}
