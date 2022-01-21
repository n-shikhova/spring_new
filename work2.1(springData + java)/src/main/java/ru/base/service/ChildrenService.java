package ru.base.service;

import ru.base.model.entity.Children;
import ru.base.service.impl.ParentServiceImpl;
import ru.base.service.impl.SchoolServiceImpl;
import java.util.List;
//в сервисах закладываем методы, которые нам нужны при работе с БД
public interface ChildrenService {
        Children addChildren(Children children, ParentServiceImpl parentService, SchoolServiceImpl schoolService);//добавить ребенка в БД
        Children updateChildren(Children children, SchoolServiceImpl schoolService);//обновить школу ребенка
        Children getById(int id);//получить ребенка по id
        List<Children> getAll();//получить список детей
}
