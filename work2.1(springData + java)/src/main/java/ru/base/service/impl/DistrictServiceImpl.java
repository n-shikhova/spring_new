package ru.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.base.model.entity.District;
import ru.base.model.repository.DistrictRepository;
import ru.base.service.DistrictService;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {//реализуем интерфейс и его методы

    @Autowired
    private DistrictRepository districtRepository;
    //реализация методов интерефейса:
    @Override
    public District addDistrict(District district) {
        District districtToSave = districtRepository.saveAndFlush(district);
        return districtToSave;
    }

    @Override
    public District getById(int id) {
        return districtRepository.getById(id);
    }

    @Override
    public List<District> getAll() {
        return districtRepository.findAll();
    }
}
