package ru.base.service;

import ru.base.model.entity.District;
import java.util.List;
//в сервисах закладываем методы, которые нам нужны при работе с БД
public interface DistrictService {
    District addDistrict(District district);//добавить район в БД
    District getById(int id);//получить район по id из БД
    List<District> getAll();//получить список районов
}
