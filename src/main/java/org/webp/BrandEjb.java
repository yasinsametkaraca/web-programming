package org.webp;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class BrandEjb {

    @PersistenceContext
    private EntityManager em;

    public Long createBrand(String name) {

        Brand brand = new Brand();
        brand.setName(name);

        em.persist(brand);
        return brand.getId();
    }

    public Long createModel(long parentId, String name) {

        Brand brand = em.find(Brand.class,parentId);
        if(brand == null){
            throw new IllegalArgumentException("Brand not found with "+parentId+" id does not exist");
        }
        Model model = new Model();
        model.setName(name);
        model.setParent(brand);
        em.persist(model);

        brand.getModels().add(model);

        return model.getId();
    }

    public List<Brand> getAllBrands(boolean withModel) {

        TypedQuery<Brand> query = em.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

       if(withModel) {
           brands.forEach(brand -> brand.getModels().size());
       }

       return brands;
    }

    public Model getModel(long id) {

        return em.find(Model.class, id);
    }

    public Brand getBrand(long id,boolean withModel) {

        Brand brand = em.find(Brand.class, id);
        if (withModel && brand != null) {
            brand.getModels().size();
        }

        return brand;
    }

}
