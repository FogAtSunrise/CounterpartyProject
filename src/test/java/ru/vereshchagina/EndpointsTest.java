package ru.vereshchagina;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.vereshchagina.exception.ResourceNotFoundException;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.service.CrudService;
import ru.vereshchagina.service.FindService;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EndpointsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CrudService crudService;

    @Autowired
    private FindService finderService;

    @Test
    @DisplayName("[GET /counterparty] Return page with list counterparty")
    void getCountercounters() throws Exception {
        mvc.perform(get("/counterparty")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[GET /counterparty/delete/{id}] Delete counter by id")
    @Transactional
    void deleteAgentById() throws Exception {
        String name = ServiceTest.getAlphaNumericString(12);
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name(name)
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);
        counter = finderService.findByName(name);
        mvc.perform(get("/counterparty/delete/" + counter.getId().toString())
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().is3xxRedirection());
        CounterpartyForm finalAgent = counter;
        assertThrows(ResourceNotFoundException.class, () -> crudService.deleteById(finalAgent.getId()));
    }

    @Test
    @DisplayName("[GET /counterparty/addcounter] Return page with add-form counter")
    void getFormCreate() throws Exception {
        mvc.perform(get("/counterparty/addcounter")
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("[GET /counterparty/updatcounter/{id}] Return page  updatcounter")
    @Transactional
    void getRedirectUpdateForm() throws Exception {
        String name = ServiceTest.getAlphaNumericString(14);
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name(name)
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);
        counter = finderService.findByName(name);

        mvc.perform(get("/counterparty/updatcounter/" + counter.getId())
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
        crudService.deleteByName(name);
    }

    @Test
    @DisplayName("[GET /counterparty/findbyname] Return findbyname page")
    void getFinderPage1() throws Exception {
        mvc.perform(get("/counterparty/findbyname")
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[GET /counterparty/findbybikandaccauntnum] Return findbybikandaccauntnum page")
    void getFinderPage2() throws Exception {
        mvc.perform(get("/counterparty/findbybikandaccauntnum")
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[POST /counterparty/addcounter] Create Counterparty")
    @Transactional
    void createPage() throws Exception {
        String name = ServiceTest.getAlphaNumericString(15);
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name(name)
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();

        mvc.perform(post("/counterparty/addcounter")
                .contentType("application/x-www-form-urlencoded")
                .param("name", counter.getName())
                .param("inn", counter.getInn())
                .param("kpp", counter.getKpp())
                .param("accountNumber", counter.getAccountNumber())
                .param("bik", counter.getBik()))
                .andExpect(status().is3xxRedirection());
        crudService.deleteByName(name);
    }

    @Test
    @DisplayName("[POST /counterparty/addcounter] Failed validation by name and number account")
    @Transactional
    void failValidOnCreatePage() throws Exception {
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);

        MvcResult mvcResult = mvc.perform(post("/counterparty/addcounter")
                .contentType("application/x-www-form-urlencoded")
                .param("name", counter.getName())
                .param("inn", counter.getInn())
                .param("kpp", counter.getKpp())
                .param("accountNumber", counter.getAccountNumber())
                .param("bik", counter.getBik()))
                .andReturn();

        assertEquals("/counterparty/addcounter", mvcResult.getModelAndView().getViewName());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST/counterparty/updatcounter] Update Counterparty")
    @Transactional
    void updatePage() throws Exception {
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);
        CounterpartyForm finderAgent = finderService.findByName("test123987");

        mvc.perform(post("/counterparty/updatcounter")
                .contentType("application/x-www-form-urlencoded")
                .param("id", finderAgent.getId().toString())
                .param("name", finderAgent.getName())
                .param("inn", finderAgent.getInn())
                .param("kpp", finderAgent.getKpp())
                .param("accountNumber", finderAgent.getAccountNumber())
                .param("bik", finderAgent.getBik()))
                .andExpect(status().is3xxRedirection());
        crudService.deleteByName("test123987");
    }


    @Test
    @DisplayName("[POST /counterparty/findbyname] Find a by name")
    @Transactional
    void findByFieldName() throws Exception {
        String name = ServiceTest.getAlphaNumericString(18);
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name(name)
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);
        CounterpartyForm finderAgent = finderService.findByName(name);

        mvc.perform(post("/counterparty/findbyname")
                .contentType("application/x-www-form-urlencoded")
                .param("name", finderAgent.getName()))
                .andExpect(status().isOk());
        crudService.deleteByName(name);
    }

    @Test
    @DisplayName("[POST /counterparty/findbyname] Failed find a countercounter by field")
    @Transactional
    void failFindByFieldName() throws Exception {
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);
        CounterpartyForm finderAgent = finderService.findByName("test123987");

        mvc.perform(post("/counterparty/findbyname")
                .contentType("application/x-www-form-urlencoded")
                .param("name", finderAgent.getName() + "test"))
                .andExpect(status().isNotFound());
        crudService.deleteByName("test123987");
    }
    @Test
    @DisplayName("[POST /counterparty/findbybikandaccauntnum] Find  by findbybikandaccauntnum")
    @Transactional
    void findByFieldBikAndAccount() throws Exception {
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);
        CounterpartyForm finderAgent = finderService.findByName("test123987");
        mvc.perform(post("/counterparty/findbybikandaccauntnum")
                .contentType("application/x-www-form-urlencoded")
                .param("bik", "040173604")
                .param("accountNumber", "40817810902003859041"))
                .andExpect(status().isOk());
        crudService.deleteByName("test123987");
    }

    @Test
    @DisplayName("[POST /counterparty/findbybikandaccauntnum] Find  by findbybikandaccauntnum")
    @Transactional
    void findByFieldBikAndAccountEx() throws Exception {
        CounterpartyForm counter = CounterpartyForm.builder()
                .id(0L)
                .name("test123987")
                .inn("7707083893")
                .kpp("123123123")
                .accountNumber("40817810902003859041")
                .bik("040173604")
                .build();
        crudService.save(counter);
        CounterpartyForm finderAgent = finderService.findByName("test123987");
        mvc.perform(post("/counterparty/findbybikandaccauntnum")
                .contentType("application/x-www-form-urlencoded")
                .param("bik", "123456789")
                .param("accountNumber", "8765810902003859041"))
                .andExpect(status().isNotFound());
        crudService.deleteByName("test123987");
    }

}
