package ru.vereshchagina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.service.CrudService;
import ru.vereshchagina.service.FindService;

import javax.validation.Valid;
import java.util.List;

/**
 * класс, предназначенный для обработки запросов от клиента и возвращения результатов
 */
@RestController

public class CounterpartyController {

    @Autowired
    private FindService findService;
    @Autowired
    private CrudService crudService;

    @RequestMapping(path = "/counterparty", method = RequestMethod.GET)
    public ModelAndView getAllCounterpartys() {
        List<CounterpartyForm> userList = findService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("counterparty");
        modelAndView.addObject("counterpartyFromServer", userList);
        return modelAndView;
    }


    @GetMapping("/counterparty/addcounter")
    public ModelAndView openAddCounter(@ModelAttribute("counterForm") CounterpartyForm counterForm) {
        return new ModelAndView("/counterparty/addcounter");
    }


    @PostMapping("/counterparty/addcounter")
        public ModelAndView addCounter(@ModelAttribute("counterForm") @Validated CounterpartyForm counterForm,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("counterForm", counterForm);
            return new ModelAndView("/counterparty/addcounter");
        }
        crudService.save(counterForm);
       return new ModelAndView("redirect:/counterparty");
    }


    @GetMapping("/counterparty/findbyname")
    public ModelAndView openFindCounterByName(@ModelAttribute("findByName") CounterpartyForm counterForm) {
        return new ModelAndView("/counterparty/findbyname");
    }

        @PostMapping("/counterparty/findbyname")
    public ModelAndView findCounterByName(@ModelAttribute("findByName") CounterpartyForm counterForm, Model model) {
   CounterpartyForm finder = new CounterpartyForm();
        finder = findService.findByName(counterForm.getName());
       model.addAttribute("finderCounter", finder);
       return new ModelAndView("/counterparty/findresult");
    }


    @GetMapping("/counterparty/findbybikandaccauntnum")
    public ModelAndView openFindCounterByBikAndNum(@ModelAttribute("findByBikAndNum") CounterpartyForm counterForm) {
        return new ModelAndView("/counterparty/findbybikandaccauntnum");
    }



    @PostMapping("/counterparty/findbybikandaccauntnum")
    public ModelAndView findCounterByBikAndNum(@ModelAttribute("findByNameBik") CounterpartyForm counterForm,
                                         Model model) {
        CounterpartyForm finder = new CounterpartyForm();
        finder = findService.findByBikAndAccountNumber(counterForm.getBik(), counterForm.getAccountNumber());
        model.addAttribute("finderCounter", finder);
        return new ModelAndView("/counterparty/findresult");
    }


    @GetMapping("/counterparty/delete/{id}")
    public ModelAndView deleteCounter(@PathVariable("id") Long id) {
        crudService.deleteById(id);
        return new ModelAndView("redirect:/counterparty");
    }

    @GetMapping("/counterparty/updatcounter/{id}")
    public ModelAndView openUpdateForm(@PathVariable("id") Long id, Model model) {
        CounterpartyForm counter = findService.findById(id);
        model.addAttribute("updateCounter", counter);
        return new ModelAndView("/counterparty/updatcounter");
    }

    @PostMapping("/counterparty/updatcounter")
    public ModelAndView updateCounter(@ModelAttribute("updateCounter") @Valid CounterpartyForm counterForm,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("updateCounter", counterForm);
            return new ModelAndView("/counterparty/updatcounter");
        }
        crudService.update(counterForm);
       return new ModelAndView("redirect:/counterparty");
    }


}
