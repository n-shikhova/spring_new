package ru.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.base.model.entity.Children;
import ru.base.model.entity.Parent;
import ru.base.model.entity.School;
import ru.base.model.repository.ChildrenRepository;
import ru.base.service.ChildrenService;
import java.util.List;

@Service
public class ChildrenServiceImpl implements ChildrenService {//реализуем интерфейс и его методы

    @Autowired
    private ChildrenRepository childrenRepository;
    //реализация методов интерефейса:
    @Override
    public Children addChildren(Children children, ParentServiceImpl parentService, SchoolServiceImpl schoolService) {
        Parent parent = parentService.getById(children.getParent().getId());//получаем родителя из БД по id
        School school = schoolService.getById(children.getSchool().getId());//получаем школу из БД по id
        children.setParent(parent);//устанавливаем родителей
        children.setSchool(school);//устанавливаем школу
        Children childrenToSave = childrenRepository.saveAndFlush(children);
        return childrenToSave;
    }

    @Override
    public Children updateChildren(Children children, SchoolServiceImpl schoolService) {
        School school = schoolService.getById(children.getSchool().getId());//получаем школу по id
        children = childrenRepository.getById(children.getId());//получаем ребенка по id
        children.setSchool(school);//устанавливаем школу
        Children childrenToSave = childrenRepository.saveAndFlush(children);
        return childrenToSave;
    }

    @Override
    public Children getById(int id) {
        return childrenRepository.getById(id);
    }

    @Override
    public List<Children> getAll() {
        return childrenRepository.findAll();
    }
}
