package daos;

import domain.GeometricForm;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GeometricFormDao implements Dao<GeometricForm> {

  EntityManager entityManager;

  public GeometricFormDao(EntityManager entityManager){
    this.entityManager = entityManager;
  }

  @Override
  public List<GeometricForm> getAll(){
    TypedQuery<GeometricForm> query =
        entityManager.createQuery("SELECT gf FROM domain.GeometricForm gf", GeometricForm.class);
    return query.getResultList();
  }

  @Override
  public void save(GeometricForm geometricForm){
    entityManager.getTransaction().begin();
    entityManager.persist(geometricForm);
    entityManager.getTransaction().commit();
  }

  @Override
  public void update(GeometricForm geometricForm){
    // TODO
  }

  @Override
  public void delete(GeometricForm geometricForm){
    entityManager.getTransaction().begin();
    entityManager.remove(geometricForm);
    entityManager.getTransaction().commit();
  }
}
