package org.webp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @BeforeEach
    public void init() {
        //her bir test calismadan once BeforeEach calistirilir
        factory = Persistence.createEntityManagerFactory("Hibernate");
        em = factory.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        //her bir test calistiktan sonra BeforeEach calistirilir
        em.close();
        factory.close();
    }

    private boolean persistInATransaction(Object... obj) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            for(Object o : obj) {
                em.persist(o);
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println("FAILED TRANSACTION: " + e.toString());
            tx.rollback();
            return false;
        }

        return true;
    }

    @Test
    public void testCar(){

        Car car = new Car();
        car.setDescription("degiseni yoktur");
        car.setColor("blue");
        car.setKilometer(10000);
        car.setYear(2000);

        boolean persisted = persistInATransaction(car);

        assertFalse(persisted); //It is false because model is missing
    }

    @Test
    public void testCarWithModel(){
        Brand brand = new Brand();
        brand.setName("Toyota");

        Model model = new Model();
        model.setName("Corolla");

        brand.getModels().add(model);
        model.setParent(brand);

        Car car = new Car();
        car.setDescription("degiseni yoktur");
        car.setYear(2000);
        car.setKilometer(1000);
        car.setColor("blue");
        car.setModel(model);
        assertTrue(persistInATransaction(brand,model,car));
    }

    @Test
    public void testTooLongName() {
        String name = new String(new char[150]);

        Brand brand = new Brand();
        brand.setName(name);
        assertFalse(persistInATransaction(brand));

        brand.setId(null);
        brand.setName("hyundai");

        assertTrue(persistInATransaction(brand));

    }
    @Test
    public void testUniqueName() {

        String name = "toyota";

        Brand brand = new Brand();
        brand.setName(name);

        assertTrue(persistInATransaction(brand));

        Brand anotherBrand = new Brand();
        anotherBrand.setName(name);

        assertFalse(persistInATransaction(anotherBrand));
    }


    private Car createCar(Model model,String color){
        Car car = new Car();
        car.setKilometer(1);
        car.setDescription("Araba Sıfırdır.");
        car.setYear(2022);
        car.setColor(color);

        car.setModel(model);

        return car;
    }
    private Model addModel(Brand brand,String modelName){
        Model model = new Model();
        model.setName(modelName);

        brand.getModels().add(model);
        model.setParent(brand);

        return model;
    }

    @Test
    public void testQueries(){
        Brand toyota= new Brand();
        toyota.setName("Toyota");

        Model corolla = addModel(toyota,"Corolla");
        Model yaris = addModel(toyota,"Yaris");
        Model auris = addModel(toyota,"Auris");

        assertTrue(persistInATransaction(toyota, corolla, yaris, auris));

        Car blue = createCar(corolla,"blue");
        Car green = createCar(corolla,"green");
        Car black = createCar(yaris,"black");
        Car white = createCar(auris,"white");

        assertTrue(persistInATransaction(blue,green,black,white));

        TypedQuery<Car> queryCorolla = em.createQuery(
                "select c from Car c where c.model.name='Corolla'",Car.class);
        List<Car> carCorolla = queryCorolla.getResultList();
        assertEquals(2, carCorolla.size()); //2 tane corolla modeli vardır.
        assertTrue(carCorolla.stream().anyMatch(c -> c.getColor().equals("blue")));
        assertTrue(carCorolla.stream().anyMatch(c -> c.getColor().equals("green")));

        TypedQuery<Car> queryToyota = em.createQuery(
                "select c from Car c where c.model.parent.name='Toyota'",Car.class);
        List<Car> all = queryToyota.getResultList();    //4 tane toyota markalı araba vardır.
        assertEquals(4, all.size());

    }

}
