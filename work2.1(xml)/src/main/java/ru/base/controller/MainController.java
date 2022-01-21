package ru.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.base.dao.Dao;
import ru.base.model.entity.Children;
import ru.base.model.entity.Parent;
import javax.validation.Valid;

@Controller//помечаем класс как контроллер
public class MainController {//основной класс контроллера

    private Dao dao;//добавляем объект, в котором реализована связь с БД

    @Autowired
    public MainController(Dao dao) {
        this.dao = dao;//внедряем зависимость
    }

    @GetMapping("/") //при переходе по адресу http://localhost:8080/
    public String watchHomePage(){
        return "pages/watchHomePage"; //- возвращаем представвление домашней страницы
    }

    @GetMapping("/watch/watchParent")//при переходе по адресу http://localhost:8080/watch/watchParent
    public String watchParent(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("parents", dao.getParentList());//передаем список родителей и назначаем этому списку имя
        return "pages/watchParent";
    }

    @GetMapping("/watch/watchSchool")//при переходе по адресу http://localhost:8080/watch/watchSchool
    public String watchSchool(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("schools", dao.getSchoolList());//передаем список школ и назначаем этому списку имя
        return "pages/watchSchool";
    }

    @GetMapping("/watch/watchDistrict")//при переходе по адресу http://localhost:8080/watch/watchDistrict
    public String watchDistrict(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("districts", dao.getDistrictList());//передаем список районов и назначаем этому списку имя
        return "pages/watchDistrict";
    }

    @GetMapping("/watch/watchChildren")//при переходе по адресу http://localhost:8080/watch/watchChildren
    public String watchChildren(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("childrens", dao.getChildrenList());//передаем список детей и назначаем этому списку имя
        return "pages/watchChildren";
    }

    @GetMapping("/watch/watchAddress")//при переходе по адресу http://localhost:8080/watch/watchAddress
    public String watchAddress(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("addresses", dao.getAddressList());//передаем список адресов и назначаем этому списку имя
        return "pages/watchAddress";
    }

    @GetMapping("/write/writeParent")//при переходе по адресу http://localhost:8080/write/writeParent
    public String writeParent(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("parents", dao.getParentList());//передаем список родителей и назначаем этому списку имя
        model.addAttribute("addresses", dao.getAddressList());//передаем список адресов и назначаем этому списку имя
        model.addAttribute("parent", new Parent());//передаем пустой (новый) объект родителя, для заполнения его полей и последующей записи в БД
        return "pages/writeParent";
    }

    @GetMapping("/write/writeChildren")//при переходе по адресу http://localhost:8080/write/writeChildren
    public String writeChildren(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("childrens", dao.getChildrenList());//передаем список детей и назначаем этому списку имя
        model.addAttribute("children", new Children());//передаем пустой (новый) объект ребенка, для заполнения его полей и последующей записи в БД
        model.addAttribute("parents", dao.getParentList());//передаем список родителей и назначаем этому списку имя
        model.addAttribute("schools", dao.getSchoolList());//передаем список школ и назначаем этому списку имя
        return "pages/writeChildren";
    }

    @GetMapping("/write/updateAddress")//при переходе по адресу http://localhost:8080/write/updateAddress
    public String updateAddress(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("parents", dao.getParentList());//передаем список родителей и назначаем этому списку имя
        model.addAttribute("addresses", dao.getAddressList());//передаем список адресов и назначаем этому списку имя
        model.addAttribute("parent", new Parent());//передаем пустой (новый) объект родителя, для заполнения его полей и последующей записи в БД
        return "pages/updateAddress";
    }
    @GetMapping("/write/updateSchool")//при переходе по адресу http://localhost:8080/write/updateSchool
    public String updateSchool(Model model){//модель используется для передачи объекта на страницу для отображения
        dao.update();//актуализируем данные из БД
        model.addAttribute("schools", dao.getSchoolList());//передаем список школ и назначаем этому списку имя
        model.addAttribute("childrens", dao.getChildrenList());//передаем список детей и назначаем этому списку имя
        model.addAttribute("children", new Children());//передаем пустой (новый) объект ребенка, для заполнения его полей и последующей записи в БД
        return "pages/updateSchool";
    }

    @PostMapping("/writeParent")//при переходе по адресу http://localhost:8080/writeParent
    public String writeParent(@ModelAttribute("parent") @Valid Parent parent){//ищем созданный объект, проверяем корректость его заполнения
        dao.writeParentToDB(parent);//передаем для записи в БД
        return "redirect:/write/writeParent";//перенаправление по адресу
    }

    @PostMapping("/writeChildren")//при переходе по адресу http://localhost:8080/writeChildren
    public String writeChildren(@ModelAttribute("children") @Valid Children children){//ищем созданный объект, проверяем корректость его заполнения
        dao.writeChildrenToDB(children);//передаем для записи в БД
        return "redirect:/write/writeChildren";//перенаправление по адресу
    }

    @PostMapping("/updateAddress")//при переходе по адресу http://localhost:8080/updateAddress
    public String updateAddress(@ModelAttribute("parent") @Valid Parent parent){//ищем созданный объект, проверяем корректость его заполнения
        dao.updateParent(parent.getId(), parent.getAddress().getId());//передаем для записи в БД
        return "redirect:/write/updateAddress";//перенаправление по адресу
    }

    @PostMapping("/updateSchool")//при переходе по адресу http://localhost:8080/updateSchool
    public String updateSchool(@ModelAttribute("children") @Valid Children children){//ищем созданный объект, проверяем корректость его заполнения
        dao.updateChildren(children.getId(), children.getSchool().getId());//передаем для записи в БД
        return "redirect:/write/updateSchool";//перенаправление по адресу
    }

}
