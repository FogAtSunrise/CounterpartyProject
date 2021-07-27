package ru.vereshchagina.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vereshchagina.model.CounterpartyForm;
import ru.vereshchagina.service.CrudService;
import ru.vereshchagina.service.FindService;


import javax.transaction.Transactional;
import java.util.List;

/**
 * класс, предназначенный для обработки запросов  и возвращения результатов
 */
@RestController
@Api(tags = "Контроллер справочника контрагентов")
public class CounterpartyController {

    @Autowired
    private FindService findService;
    @Autowired
    private CrudService crudService;


    /**
     * GET-запрос загрузки страницы справочника со списком контрагентов
     *
     * @return модель для отображения страницы
     */
    @ApiOperation(value = "Загрузка списка контрагентов",
            notes = "Метод загружает страницу с таблицей контрагентов")
    @GetMapping("/counterparty")
    public ModelAndView getAllCounterpartys() {
        List<CounterpartyForm> userList = findService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("counterparty");
        modelAndView.addObject("counterpartyFromServer", userList);
        return modelAndView;
    }


    /**
     * GET-запрос, загружающий форму для добавления нового контрагента
     *
     * @param counterForm объект контрагента
     * @return модель для отображения страницы
     */
    @ApiOperation(value = "Загрузка формы для добавления нового контрагента",
            notes = "Метод загружает страницу с формой для добавления")
    @GetMapping("/counterparty/addcounter")
    public ModelAndView openAddCounter(@ModelAttribute("counterForm") CounterpartyForm counterForm) {
        return new ModelAndView("/counterparty/addcounter");
    }


    /**
     * POST-запрос сохранения нового контрагента в БД
     *
     * @param counterForm   добавленный контрагент
     * @param bindingResult ошибки валидации
     * @param model         модель страницы
     * @return модель для отображения конечной страницы
     */
    @ApiOperation(value = "Сохранение добавленного контрагента",
            notes = "Метод сохраняет данные нового контрагента в БД")
    @PostMapping("/counterparty/addcounter")
    public ModelAndView addCounter(@ModelAttribute("counterForm") @Validated CounterpartyForm counterForm,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("counterForm", counterForm);
            return new ModelAndView("/counterparty/addcounter");
        }
        crudService.save(counterForm);
        return new ModelAndView("redirect:/counterparty");
    }


    /**
     * GET-запрос загрузки страницы поиска контрагента по наименованию
     *
     * @param counterForm объект контрагента
     * @return модель для отображения конечной страницы
     */
    @ApiOperation(value = "Загрузка страницы для поиска контрагента по наименованию",
            notes = "Метод загружает страницу с формой для поиска контрагента по наименованию")
    @GetMapping("/counterparty/findbyname")
    public ModelAndView openFindCounterByName(@ModelAttribute("findByName") CounterpartyForm counterForm) {
        return new ModelAndView("/counterparty/findbyname");
    }


    /**
     * POST-запрос поиска контрагента по наименованию
     *
     * @param counterForm найденный контрагент
     * @param model       модель страницы
     * @returnмодель для отображения конечной страницы
     */
    @ApiOperation(value = "Поиск контрагента по наименованию",
            notes = "Метод ищет данные контрагента и выводит их в форме")

    @PostMapping("/counterparty/findbyname")
    public ModelAndView findCounterByName(@ModelAttribute("findByName") CounterpartyForm counterForm, Model model) {
        CounterpartyForm finder = new CounterpartyForm();
        finder = findService.findByName(counterForm.getName());
        model.addAttribute("finderCounter", finder);
        return new ModelAndView("/counterparty/findresult");
    }

    /**
     * GET-запрос загрузки страницы поиска контрагента по номеру счета и БИК
     *
     * @param counterForm объект контрагента
     * @return модель для отображения конечной страницы
     */
    @ApiOperation(value = "Загрузка страницы для поиска контрагента по номеру счета и БИК",
            notes = "Метод загружает страницу с формой для поиска контрагента по номеру счета и БИК")
    @GetMapping("/counterparty/findbybikandaccauntnum")
    public ModelAndView openFindCounterByBikAndNum(@ModelAttribute("findByBikAndNum") CounterpartyForm counterForm) {
        return new ModelAndView("/counterparty/findbybikandaccauntnum");
    }


    /**
     * POST-запрос поиска контрагента по номеру счета и БИК
     *
     * @param counterForm найденный контрагент
     * @param model       модель страницы
     * @return модель для отображения конечной страницы
     */

    @ApiOperation(value = "Поиск контрагента по номеру счета и БИК",
            notes = "Метод ищет данные контрагента и выводит их в форме")
    @PostMapping("/counterparty/findbybikandaccauntnum")
    public ModelAndView findCounterByBikAndNum(@ModelAttribute("findByNameBik") CounterpartyForm counterForm,
                                               Model model) {
        CounterpartyForm finder = new CounterpartyForm();
        finder = findService.findByBikAndAccountNumber(counterForm.getBik(), counterForm.getAccountNumber());
        model.addAttribute("finderCounter", finder);
        return new ModelAndView("/counterparty/findresult");
    }

    /**
     * GET-запрос на удаление записи выбранного контрагента
     *
     * @param id ИД контрагента
     * @return модель для отображения конечной страницы
     */

    @ApiOperation(value = "Удаление данных контрагента",
            notes = "Этот метод удаляет данные контрагента по ИД и перенаправляет на страницу со списком контрагентов")
    @GetMapping("/counterparty/delete/{id}")
    public ModelAndView deleteCounter(@PathVariable("id") @Parameter(description = "ИД контрагента") Long id) {
        crudService.deleteById(id);
        return new ModelAndView("redirect:/counterparty");
    }


    /**
     * GET-запрос загрузки формы для изменения данных выбранного контрагента
     *
     * @param id    ИД контрагента
     * @param model модель страницы
     * @return модель для отображения конечной страницы
     */
    @ApiOperation(value = "Загрузка формы для изменения данных выбранного контрагента",
            notes = "Метод загружеает страницу обновления данных с моделью найденного контрагента")
    @GetMapping("/counterparty/updatcounter/{id}")
    public ModelAndView openUpdateForm(@PathVariable("id") @Parameter(description = "ИД контрагента") Long id, Model model) {
        CounterpartyForm counter = findService.findById(id);
        model.addAttribute("updateCounter", counter);
        return new ModelAndView("/counterparty/updatcounter");
    }


    /**
     * POST-запрос обновления контрагент
     *
     * @param counterForm   выбранный контрагент
     * @param bindingResult ошибки валидации
     * @param model         модель страницы
     * @return модель для отображения конечной страницы
     */
    @ApiOperation(value = "Обновление данных контрагента",
            notes = "Метод обновляет данные контрагента")
    @PostMapping("/counterparty/updatcounter")
    public ModelAndView updateCounter(@ModelAttribute("updateCounter") @Validated  CounterpartyForm counterForm,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("updateCounter", counterForm);
            return new ModelAndView("/counterparty/updatcounter");
        }
        crudService.update(counterForm);
        return new ModelAndView("redirect:/counterparty");
    }

    /**
     * GET-запрос загрузки страницы удаления контрагента по наименованию
     *
     * @param counterForm объект контрагента
     * @return модель для отображения конечной страницы
     */
    @ApiOperation(value = "Загрузка страницы для удаления контрагента по наименованию",
            notes = "Метод загружает страницу с формой для удаления контрагента по наименованию")
    @GetMapping("/counterparty/deletebyname")
    public ModelAndView openDelrByName(@ModelAttribute("deleteByName") CounterpartyForm counterForm) {
        return new ModelAndView("/counterparty/deletebyname");
    }
    /**
     * POST-запрос удаления контрагента по имени
     * @param counterForm удаляемый контрагент
     * @return модель для отображения конечной страницы с переданной информацией
     */
    @ApiOperation(value = "Удаляет контрагента по имени",
            notes = "Этот метод удаляет контрагента по имени")
    @PostMapping("/counterparty/deletebyname")
    @Transactional
    public ModelAndView deleteByName(@ModelAttribute("deleteByName") CounterpartyForm counterForm) {
        crudService.deleteByName(counterForm.getName());
        return new ModelAndView("redirect:/counterparty");
    }
}
