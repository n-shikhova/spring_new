package ru.base.service;

import ru.base.model.entity.Parent;
import ru.base.service.impl.AddressServiceImpl;
import java.util.List;
//в сервисах закладываем методы, которые нам нужны при работе с БД
public interface ParentService {
    Parent addParent(Parent parent, AddressServiceImpl addressService);//добавить родителя
    Parent updateParent(Parent parent, AddressServiceImpl addressService);//обновить адрес проживания родителей
    Parent getById(int id);//получить по id
    List<Parent> getAll(); //получить список родителей
}
