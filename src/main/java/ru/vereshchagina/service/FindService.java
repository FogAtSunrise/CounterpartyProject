package ru.vereshchagina.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vereshchagina.entity.Counterparty;
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


//    public List<CounterpartyForm> findByName(String name) {
//        List<Counterparty> findCounters = usersRepository.findFirstByName(name);
//        List<CounterpartyForm> findCountersForm = new ArrayList<>();
//    if(findCounters.size()!=0)
//        for(Counterparty counter:findCounters)
//        {//findCountersForm.add(CounterpartyForm.from(counter));
//findCountersForm.add(CounterpartyForm.builder()
//        .name(counter.getName())
//        .inn(counter.getInn())
//        .kpp(counter.getKpp())
//        .accountNumber(counter.getAccountNumber())
//        .bik(counter.getBik())
//        .build());
//        }
//        return findCountersForm;
//    }


    public CounterpartyForm findByName(String name) {
        Counterparty agent = usersRepository.findByName(name);
        CounterpartyForm webAgent = mapperFacade.map(agent, CounterpartyForm.class);
        return webAgent;
    }


    public CounterpartyForm findByBikAndAccountNumber(String bik, String numberAccount) {
        Counterparty agent = usersRepository.findByBikAndAccountNumber(bik, numberAccount);
        CounterpartyForm webAgent = mapperFacade.map(agent, CounterpartyForm.class);
        return webAgent;
    }

    public CounterpartyForm findById(Long id) {
        Optional<Counterparty> agentDAO = usersRepository.findById(id);
            CounterpartyForm finderAgent = mapperFacade.map(agentDAO.get(), CounterpartyForm.class);
            return finderAgent;
    }

//    public List<CounterpartyForm> findByBikAndAccountNumber(String bik, String accountNumber) {
//        List<Counterparty> agent = usersRepository.findByBikAndAccountNumber(bik, accountNumber);
//        List<CounterpartyForm> webAgent = mapperFacade.mapAsList(agent, CounterpartyForm.class);
//
//        return webAgent;
//    }

//    public List<CounterpartyForm> findFirstByBikAndAccountNumber(String bik, String accountNumber) {
//        List<Counterparty> findCounters = usersRepository.findFirstByBikAndAccountNumber(bik, accountNumber);
//        List<CounterpartyForm> findCountersForm = new ArrayList<>();
//        if(findCounters.size()!=0)
//            for(Counterparty counter:findCounters)
//            {//findCountersForm.add(CounterpartyForm.from(counter));
//                findCountersForm.add(CounterpartyForm.builder()
//                        .name(counter.getName())
//                        .inn(counter.getInn())
//                        .kpp(counter.getKpp())
//                        .accountNumber(counter.getAccountNumber())
//                        .bik(counter.getBik())
//                        .build());
//            }
//        return findCountersForm;
//    }

//    public CounterpartyForm findByName(String name) {
//        List<Counterparty> agent = usersRepository.findByName(name);
//        CounterpartyForm webAgent = mapperFacade.map(agent, CounterpartyForm.class);
//        return webAgent;
//    }
//
//    public CounterpartyForm findByBikAndNumberAccount(String bik, String numberAccount) {
//        Counterparty agent = usersRepository.findByBikAndNumberAccount(bik, numberAccount);
//        CounterpartyForm webAgent = mapperFacade.map(agent, CounterpartyForm.class);
//        return webAgent;
//    }
//
//    public List<Counterparty> findAllOf(String bik, String accountNumber){
//        return usersRepository.findFirstByBikAndAccountNumber(bik, accountNumber);
//    }
//
//
//    public List<Counterparty> test() {
//        return usersRepository.findFirstByBikAndAccountNumber("xvxv", "tsfs");
//    }
//
//    public List<CounterpartyForm> test2() {
//        List<Counterparty> findCounters = usersRepository.findFirstByBikAndAccountNumber("xvxv", "tsfs");
//        List<CounterpartyForm> findCountersForm = new ArrayList<>();
//        if(findCounters.size()!=0)
//            for(Counterparty counter:findCounters)
//            {//findCountersForm.add(CounterpartyForm.from(counter));
//                findCountersForm.add(CounterpartyForm.builder()
//                        .name(counter.getName())
//                        .inn(counter.getInn())
//                        .kpp(counter.getKpp())
//                        .accountNumber(counter.getAccountNumber())
//                        .bik(counter.getBik())
//                        .build());
//            }
//        return findCountersForm;
//
//    }
//
//    public List<Counterparty> test1() {
//        return usersRepository.findFirstByName("aaa");
//    }
}