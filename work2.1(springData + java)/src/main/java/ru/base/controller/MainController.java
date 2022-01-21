package ru.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.base.model.entity.Children;
import ru.base.model.entity.Parent;
import ru.base.service.impl.*;
import javax.validation.Valid;

@Controller//помечаем класс как контроллер
public class MainController {//основной класс контроллера
    //объекты сервиса для доступа к сущностям БД:
    private AddressServiceImpl addressService;
    private ChildrenServiceImpl childrenService;
    private DistrictServiceImpl districtService;
    private ParentServiceImpl parentService;
    private SchoolServiceImpl schoolService;

    @Autowired
    public MainController(AddressServiceImpl addressService,
                          ChildrenServiceImpl childrenService,
                          DistrictServiceImpl districtService,
                          ParentServiceImpl parentService,
                          SchoolServiceImpl schoolService) {
        this.addressService = addressService;
        this.childrenService = childrenService;
        this.districtService = districtService;
        this.parentService = parentService;
        this.schoolService = schoolService;
    }//коструктор

    @GetMapping("/")//при переходе по адресу http://localhost:8080/
    public String watchHomePage(){
        return "pages/watchHomePage";
    } //- возвращаем представвление домашней страницы

    @GetMapping("/watch/watchParent")//при переходе по адресу http://localhost:8080/watch/watchParent
    public String watchParent(Model model){//модель используется для передачи объекта на страницу для отображения
        model.addAttribute("parents", parentService.getAll());//передаем список родителей и назначаем этому списку имя
        return "pages/watchParent";
    }

    @GetMapping("/watch/watchSchool")//при переходе по адресу http://localhost:8080/watch/watchSchool
    public String watchSchool(Model model){//модель используется для передачи объекта на страницу для отображения
        model.addAttribute("schools", schoolService.getAll());//передаем список школ и назначаем этому списку имя
        return "pages/watchSchool";
    }

    @GetMapping("/watch/watchDistrict")//при переходе по адресу http://localhost:8080/watch/watchDistrict
    public String watchDistrict(Model model){//модель используется для передачи объекта на страницу для отображения
        model.addAttribute("districts", districtService.getAll());//передаем список районов и назначаем этому списку имя
        return "pages/watchDistrict";
    }

    @GetMapping("/watch/watchChildren")//при переходе по адресу http://localhost:8080/watch/watchChildren
    public String watchChildren(Model model){//модель используется для передачи объекта на страницу для отображения
        model.addAttribute("childrens", childrenService.getAll());//передаем список детей и назначаем этому списку имя
        return "pages/watchChildren";
    }

    @GetMapping("/watch/watchAddress")//при переходе по адресу http://localhost:8080/watch/watchAddress
    public String watchAddress(Model model){//модель используется для передачи объекта на страницу для отображения
        model.addAttribute("addresses", addressService.getAll());//передаем список адресов и назначаем этому списку имя
        return "pages/watchAddress";
    }

    @GetMapping("/write/writeParent")//при переходе по адресу http://localhost:8080/write/writeParent
    public String writeParent(Model model){
        model.addAttribute("parents", parentService.getAll());//передаем список родителей и назначаем этому списку имя
        model.addAttribute("addresses", addressService.getAll());//передаем список адресов и назначаем этому списку имя
        model.addAttribute("parent", new Parent());//передаем пустой (новый) объект родителя, для заполнения его полей и последующей записи в БД
        return "pages/writeParent";
    }

    @GetMapping("/write/writeChildren")//при переходе по адресу http://localhost:8080/write/writeChildren
    public String writeChildren(Model model){//модель используется для передачи объекта на страницу для отображения
        model.addAttribute("childrens", childrenService.getAll());//передаем список детей и назначаем этому списку имя
        model.addAttribute("children", new Children());//передаем пустой (новый) объект ребенка, для заполнения его полей и последующей записи в БД
        model.addAttribute("parents", parentService.getAll());//передаем список родителей и назначаем этому списку имя
        model.addAttribute("schools", schoolService.getAll());//передаем список школ и назначаем этому списку имя
        return "pages/writeChildren";
    }

    @GetMapping("/write/updateAddress")//при переходе по адресу http://localhost:8080/write/updateAddress
    public String updateAddress(Model model){//модель используется для передачи объекта на страницу для отображения
        model.addAttribute("parents", parentService.getAll());//передаем список родителей и назначаем этому списку имя
        model.addAttribute("addresses", addressService.getAll());//передаем список адресов и назначаем этому списку имя
        model.addAttribute("parent", new Parent());//передаем пустой (новый) объект родителя, для заполнения его полей и последующей записи в БД
        return "pages/updateAddress";
    }

    @GetMapping("/write/updateSchool")//при переходе по адресу http://localhost:8080/write/updateSchool
    public String updateSchool(Model model){
        model.addAttribute("schools", schoolService.getAll());//передаем список школ и назначаем этому списку имя
        model.addAttribute("childrens", childrenService.getAll());//передаем список детей и назначаем этому списку имя
        model.addAttribute("children", new Children());//передаем пустой (новый) объект ребенка, для заполнения его полей и последующей записи в БД
        return "pages/updateSchool";
    }

    @PostMapping("/writeParent")//при переходе по адресу http://localhost:8080/writeParent
    public String writeParent(@ModelAttribute("parent") @Valid Parent parent){//ищем созданный объект, проверяем корректость его заполнения
        parentService.addParent(parent, addressService);//вызываем метод записи в БД
        return "redirect:/write/writeParent";//перенаправление по адресу
    }

    @PostMapping("/writeChildren")//при переходе по адресу http://localhost:8080/writeChildren
    public String writeChildren(@ModelAttribute("children") @Valid Children children){//ищем созданный объект, проверяем корректость его заполнения
        childrenService.addChildren(children, parentService, schoolService);//вызываем метод записи в БД
        return "redirect:/write/writeChildren";//перенаправление по адресу
    }

    @PostMapping("/updateAddress")//при переходе по адресу http://localhost:8080/updateAddress
    public String updateAddress(@ModelAttribute("parent") @Valid Parent parent){//ищем созданный объект, проверяем корректость его заполнения
        parentService.updateParent(parent, addressService);//вызываем метод записи в БД
        return "redirect:/write/updateAddress";//перенаправление по адресу
    }

    @PostMapping("/updateSchool")//при переходе по адресу http://localhost:8080/updateSchool
    public String updateSchool(@ModelAttribute("children") @Valid Children children){//ищем созданный объект, проверяем корректость его заполнения
        childrenService.updateChildren(children, schoolService);//вызываем метод записи в БД
        return "redirect:/write/updateSchool";//перенаправление по адресу
    }

}
