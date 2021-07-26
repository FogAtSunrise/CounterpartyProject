package ru.vereshchagina;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.repository.CounterpartyRepository;
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
     * @param n количество символов в строке
     * @return строка
     */
    static String getAlphaNumericString(int n) {
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
        for (int i = 0; i < 10; i++) {

            CounterpartyForm counter = CounterpartyForm.builder()
                    .name(getAlphaNumericString(i + 5))
                    .inn("12345")
                    .kpp("1234567")
                    .accountNumber("123")
                    .bik("123456")
                    .build();
            list.add(counter);
            assertFalse(repository.findByName(counter.getName()).isPresent());
            crudService.save(counter);
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(repository.findByName(list.get(i).getName()).isPresent());
            repository.deleteByName(list.get(i).getName());
        }
    }

    @Test
    @DisplayName("Testing method update()")
    @Transactional
    void testUpdate() {
        CounterpartyForm counter = CounterpartyForm.builder()
                .name("test1")
                .inn("1237083893")
                .kpp("532123123")
                .accountNumber("7564510902003859041")
                .bik("87653604")
                .build();
        crudService.save(counter);
        counter = finderService.findByName("test1");
        CounterpartyForm counterUpdate = CounterpartyForm.builder()
                .id(counter.getId())
                .name("test2")
                .inn("7707083892")
                .kpp("123123122")
                .accountNumber("40817810902003859042")
                .bik("040173602")
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
    @Transactional
    void testDeleteById() {
        CounterpartyForm counter = CounterpartyForm.builder()
                .name("test123")
                .inn("3707083893")
                .kpp("323123123")
                .accountNumber("40917810902003859041")
                .bik("046173604")
                .build();
        crudService.save(counter);
        counter = finderService.findByName("test123");
        CounterpartyForm finalAgent = counter;
        assertDoesNotThrow(() -> crudService.deleteById(finalAgent.getId()));

    }

    @Test
    @DisplayName("Testing method findAll()")
    @Transactional
    void testFindAll() {
        repository.deleteAll();
        int countElem = 0;
        for (int i = 0; i < Math.random() * 20; i++) {
            CounterpartyForm counter = CounterpartyForm.builder()
                    .name("test" + i)
                    .inn("123" + i)
                    .kpp("123" + i)
                    .accountNumber("123" + i)
                    .bik("123" + i)
                    .build();
            crudService.save(counter);
            countElem++;
        }
        assertEquals(countElem, finderService.findAll().size());
        repository.deleteAll();
    }

    @Test
    @DisplayName("Testing method findByName()")
    @Transactional
    void testFindById() {
        String name = getAlphaNumericString(11);
        CounterpartyForm counter = CounterpartyForm.builder()
                .name(name)
                .inn("123")
                .kpp("765")
                .accountNumber("345")
                .bik("123")
                .build();
        crudService.save(counter);
        counter = finderService.findByName(name);
        assertEquals(counter, finderService.findById(counter.getId()));
        crudService.deleteById(counter.getId());
    }

    @Test
    @DisplayName("Testing method findByBikAndNumberAccount()")
    @Transactional
    void testFindByBikAndNum() {
        CounterpartyForm counter = CounterpartyForm.builder()
                .name(getAlphaNumericString(8))
                .inn("98796")
                .kpp("7567")
                .accountNumber("2452346")
                .bik("23523452345")
                .build();
        crudService.save(counter);
        counter = finderService.findByName(counter.getName());
        assertEquals(counter, finderService.findByBikAndAccountNumber(counter.getBik(), counter.getAccountNumber()));
        crudService.deleteById(counter.getId());
    }
}
