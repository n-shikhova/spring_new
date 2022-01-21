package ru.base.dao;

import org.springframework.stereotype.Component;
import ru.base.model.entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
public class Dao {//класс реализации соединения с БД
    private EntityManagerFactory entityManagerFactory; //интерфейс ORM для управления персистентными сущностями
    private EntityManager entityManager;// выполняет основные операции над сущностями
    private List<Parent> parentList;//лист родителей
    private List<Address> addressList;//писок адресов
    private List<Children> childrenList;//список детей
    private List<District> districtList;//список районов
    private List<School> schoolList;//список школ

    public Dao() {//конструктор
        //конфигурируем EntityManager при помощи конфигурации, записанной в xml файле:
        entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit"); //подставляем файл, в котором описано соединение с БД
        entityManager = null;
    }

    private void createAndBeginEntityManager(){//закрытиые и начало работы мнеджера сущностей
        entityManager = entityManagerFactory.createEntityManager();//получаем менеджер сущностей
        entityManager.getTransaction().begin();//открываем "сессию" с базой данных
    }
    private void closeAndCommitEntityManager(){//сохранение изменений в БД и закрытие мнеджера сущностей
        entityManager.getTransaction().commit();//запись в БД
        entityManager.close();//зыкрываем менеджер сущностей
    }
    public void update(){//обновляем все данные из БД
        createAndBeginEntityManager();
        parentList = entityManager.createQuery("select parent from Parent parent").getResultList();
        schoolList = entityManager.createQuery("select school from School school").getResultList();
        districtList = entityManager.createQuery("select district from District district").getResultList();
        childrenList = entityManager.createQuery("select children from Children children").getResultList();
        addressList = entityManager.createQuery("select address from Address address").getResultList();
        closeAndCommitEntityManager();
    }
    public void writeParentToDB(Parent parent){//записываем нового родителя в БД
        createAndBeginEntityManager();
        Address address = entityManager.find(Address.class, parent.getAddress().getId());//получаем адрес из БД
        parent.setAddress(address);//устанавливаем адрес
        entityManager.persist(parent);//подготовка к записи в БД - добавление в контекст
        closeAndCommitEntityManager();
    }
    public void writeChildrenToDB(Children children){//записть нового ребенка в БД
        createAndBeginEntityManager();
        Parent parent = entityManager.find(Parent.class, children.getParent().getId());//получаем родителя из БД
        School school = entityManager.find(School.class, children.getSchool().getId());//полчаем Школу из БД
        children.setParent(parent);//устанавливаем родителей
        children.setSchool(school);//устанавливаем школу
        entityManager.persist(children);//подготовка к записи в БД - добавление в контекст
        closeAndCommitEntityManager();
    }
    public void updateParent(int parent_id, int address_id){//обновляем адресс родителей
        createAndBeginEntityManager();
        Parent parent = entityManager.find(Parent.class, parent_id);//получаем родителя у которого нужно изменить адрес
        Address address = entityManager.find(Address.class, address_id);//получаем новый адрес
        parent.setAddress(address);//устанавливаем новый адрес
        entityManager.persist(parent);//подготовка к записи в БД - добавление в контекст
        closeAndCommitEntityManager();
    }
    public void updateChildren(int children_id, int school_id){//обновляем учебное заведение
        createAndBeginEntityManager();
        Children children = entityManager.find(Children.class, children_id);//получаем ребенка, у которого надо заменить школу
        School school = entityManager.find(School.class, school_id);//получаем школу
        children.setSchool(school);//устанавливаем школу
        entityManager.persist(children);//подготовка к записи в БД - добавление в контекст
        closeAndCommitEntityManager();
    }

    //гетеры и сетеры:

    public List<Parent> getParentList() {
        return parentList;
    }

    public void setParentList(List<Parent> parentList) {
        this.parentList = parentList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Children> childrenList) {
        this.childrenList = childrenList;
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }
}
