package ru.base.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.base.model.entity.Address;

//создаем репозитории для каждой сущности. В качестве первого параметра используем имя класса.
//в качестве второго параметра используем оберточный класс для поля ID
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
