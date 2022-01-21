package ru.base.service;

import ru.base.model.entity.Address;
import java.util.List;

//в сервисах закладываем методы, которые нам нужны при работе с БД
public interface AddressService {
    Address addAddress(Address address);//добавление нового адреса
    Address getById(int id);//получить адрес по id
    List<Address> getAll();//получить список адресов
}
