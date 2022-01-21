package ru.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.base.model.entity.School;
import ru.base.model.repository.SchoolRepository;
import ru.base.service.SchoolService;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {//реализуем интерфейс и его методы

    @Autowired
    private SchoolRepository schoolRepository;
    //реализация методов интерефейса:
    @Override
    public School addSchool(School school) {
        School parentToSave = schoolRepository.saveAndFlush(school);
        return parentToSave;
    }

    @Override
    public School getById(int id) {
        return schoolRepository.getById(id);
    }

    @Override
    public List<School> getAll() {
        return schoolRepository.findAll();
    }
}