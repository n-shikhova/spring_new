package ru.base.service;

import ru.base.model.entity.School;
import java.util.List;
//в сервисах закладываем методы, которые нам нужны при работе с БД
public interface SchoolService {
    School addSchool(School school);//добавить школу
    School getById(int id);//получить по id
    List<School> getAll();//получить весь список школ
}
