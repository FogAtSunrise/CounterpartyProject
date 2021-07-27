package ru.vereshchagina;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.dao.CounterpartyRepository;
import ru.vereshchagina.service.CrudService;
import ru.vereshchagina.service.FindService;

import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private CrudService crudService;

    @Autowired
    private FindService finderService;

    @Autowired
    private CounterpartyRepository repository;

    /**
     * вспомогательный метод для генерации строки
     * @return строка
     */
    static String getRondomStr() {
        int n = (int) (1+20 * Math.random());
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }


    @Test
    @DisplayName("Testing method save()")
    @Transactional
    void testSave() {
        List<CounterpartyForm> list = new ArrayList<>();
        int n =(int) (1+20 * Math.random());
        for (int i = 0; i < n; i++) {
            CounterpartyForm counter = CounterpartyForm.builder()
                    .name(getRondomStr())
                    .inn(getRondomStr())
                    .kpp(getRondomStr())
                    .accountNumber(getRondomStr())
                    .bik(getRondomStr())
                    .build();
            list.add(counter);
            assertFalse(repository.findByName(counter.getName()).isPresent());
            crudService.save(counter);
        }
        for (int i = 0; i < n; i++) {
            assertTrue(repository.findByName(list.get(i).getName()).isPresent());
            repository.deleteByName(list.get(i).getName());
        }
    }

    @Test
    @DisplayName("Testing method update()")

    void testUpdate() {
        String name = getRondomStr();
        CounterpartyForm counter = CounterpartyForm.builder()
                .name(name)
                .inn(getRondomStr())
                .kpp(getRondomStr())
                .accountNumber(getRondomStr())
                .bik(getRondomStr())
                .build();
        crudService.save(counter);
        counter = finderService.findByName(name);
        CounterpartyForm counterUpdate = CounterpartyForm.builder()
                .id(counter.getId())
                .name(getRondomStr())
                .inn(getRondomStr())
                .kpp(getRondomStr())
                .accountNumber(getRondomStr())
                .bik(getRondomStr())
                .build();
        crudService.update(counterUpdate);
        assertTrue(repository.findById(counterUpdate.getId()).isPresent());
        assertEquals(counterUpdate.getName(), repository.findById(counterUpdate.getId()).get().getName());
        assertEquals(counterUpdate.getInn(), repository.findById(counterUpdate.getId()).get().getInn());
        assertEquals(counterUpdate.getKpp(), repository.findById(counterUpdate.getId()).get().getKpp());
        assertEquals(counterUpdate.getAccountNumber(), repository.findById(counterUpdate.getId()).get().getAccountNumber());
        assertEquals(counterUpdate.getBik(), repository.findById(counterUpdate.getId()).get().getBik());
        repository.deleteById(counterUpdate.getId());
    }

    @Test
    @DisplayName("Testing deleteById()")
    void testDeleteById() {
        String name = getRondomStr();
        CounterpartyForm counter = CounterpartyForm.builder()
                .name(name)
                .inn(getRondomStr())
                .kpp(getRondomStr())
                .accountNumber(getRondomStr())
                .bik(getRondomStr())
                .build();
        crudService.save(counter);
        counter = finderService.findByName(name);
        CounterpartyForm finalAgent = counter;
        assertDoesNotThrow(() -> crudService.deleteById(finalAgent.getId()));

    }

    @Test
    @DisplayName("Testing method deleteByName()")
    @Transactional
    void testDeleteByName() {
        String name = getRondomStr();
        CounterpartyForm agent = CounterpartyForm.builder()
                .name(name)
                .inn(getRondomStr())
                .kpp(getRondomStr())
                .accountNumber(getRondomStr())
                .bik(getRondomStr())
                .build();
        crudService.save(agent);
        agent = finderService.findByName(name);
        CounterpartyForm finalAgent = agent;
        assertDoesNotThrow(() -> crudService.deleteByName(finalAgent.getName()));
    }

    @Test
    @DisplayName("Testing method findAll()")

    void testFindAll() {
        repository.deleteAll();
        int countElem = 0;
        for (int i = 0; i < Math.random() * 20; i++) {
            CounterpartyForm counter = CounterpartyForm.builder()
                    .name(getRondomStr())
                    .inn(getRondomStr())
                    .kpp(getRondomStr())
                    .accountNumber(getRondomStr())
                    .bik(getRondomStr())
                    .build();
            crudService.save(counter);
            countElem++;
        }
        assertEquals(countElem, finderService.findAll().size());
        repository.deleteAll();
    }

    @Test
    @DisplayName("Testing method findByName()")

    void testFindById() {
        String name = getRondomStr();
        CounterpartyForm counter = CounterpartyForm.builder()
                .name(name)
                .inn(getRondomStr())
                .kpp(getRondomStr())
                .accountNumber(getRondomStr())
                .bik(getRondomStr())
                .build();
        crudService.save(counter);
        counter = finderService.findByName(name);
        assertEquals(counter, finderService.findById(counter.getId()));
        crudService.deleteById(counter.getId());
    }

    @Test
    @DisplayName("Testing method findByBikAndNumberAccount()")

    void testFindByBikAndNum() {
        CounterpartyForm counter = CounterpartyForm.builder()
                .name(getRondomStr())
                .inn(getRondomStr())
                .kpp(getRondomStr())
                .accountNumber(getRondomStr())
                .bik(getRondomStr())
                .build();
        crudService.save(counter);
        counter = finderService.findByName(counter.getName());
        assertEquals(counter, finderService.findByBikAndAccountNumber(counter.getBik(), counter.getAccountNumber()));
        crudService.deleteById(counter.getId());
    }
}
