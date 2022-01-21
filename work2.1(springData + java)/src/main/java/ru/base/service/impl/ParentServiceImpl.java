package ru.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.base.model.entity.Address;
import ru.base.model.entity.Parent;
import ru.base.model.repository.ParentRepository;
import ru.base.service.ParentService;
import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {//реализуем интерфейс и его методы

    @Autowired
    private ParentRepository parentRepository;
    //реализация методов интерефейса:
    @Override
    public Parent addParent(Parent parent, AddressServiceImpl addressService) {
        Address address = addressService.getById(parent.getAddress().getId());//получаем адрес по id
        parent.setAddress(address);//устанавливаем адрес
        Parent parentToSave = parentRepository.saveAndFlush(parent);
        return parentToSave;
    }

    @Override
    public Parent updateParent(Parent parent, AddressServiceImpl addressService) {
        Address address = addressService.getById(parent.getAddress().getId());//получаем адрес по id
        parent = parentRepository.getById(parent.getId());//получем родителя по id
        parent.setAddress(address);//устанавливаем адрес
        Parent parentToSave = parentRepository.saveAndFlush(parent);
        return parentToSave;
    }

    @Override
    public Parent getById(int id) {
        return parentRepository.getById(id);
    }

    @Override
    public List<Parent> getAll() {
        return parentRepository.findAll();
    }
}
