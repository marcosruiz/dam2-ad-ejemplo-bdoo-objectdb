package daos;

import domain.GeometricForm;

import javax.persistence.EntityManager;
import java.util.List;

public class GeometricFormDao implements Dao<GeometricForm> {

  EntityManager entityManager;

  public GeometricFormDao(EntityManager entityManager){
    this.entityManager = entityManager;
  }

  @Override
  public List<GeometricForm> getAll(){
    return null;
  }

  @Override
  public void save(GeometricForm geometricForm){
    entityManager.getTransaction().begin();
    entityManager.persist(geometricForm);
    entityManager.getTransaction().commit();
  }

  @Override
  public void update(GeometricForm geometricForm){

  }

  @Override
  public void delete(GeometricForm geometricForm){

  }
}
