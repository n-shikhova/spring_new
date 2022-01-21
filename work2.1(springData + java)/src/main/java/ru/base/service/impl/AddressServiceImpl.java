package ru.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.base.model.entity.Address;
import ru.base.model.repository.AddressRepository;
import ru.base.service.AddressService;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {//реализуем интерфейс и его методы

    @Autowired
    private AddressRepository addressRepository;

    //реализация методов интерефейса:
    @Override
    public Address addAddress(Address address) {
        Address addressToSave = addressRepository.saveAndFlush(address);
        return addressToSave;
    }

    @Override
    public Address getById(int id) {
        return addressRepository.getById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
