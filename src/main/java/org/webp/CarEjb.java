package org.webp;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CarEjb {

    @PersistenceContext
    private EntityManager em;

    public long createCar(long modelId,String color,int year,int kilometer,String description){

        Model model = em.find(Model.class,modelId);

        if(model == null){
            throw new IllegalArgumentException("Model not found. Model "+modelId+" does not exist");
        }

        Car car = new Car();
        car.setModel(model);
        car.setColor(color);
        car.setYear(year);
        car.setKilometer(kilometer);
        car.setDescription(description);

        em.persist(car);

        return car.getId();
    }

    public List<Car> getCars(){

        TypedQuery<Car> query = em.createQuery("select c from Car c", Car.class);

        return query.getResultList();
    }

    public Car getCar(long id){

        return em.find(Car.class, id); //bir tane araba getirmesi icindir.

    }





}
