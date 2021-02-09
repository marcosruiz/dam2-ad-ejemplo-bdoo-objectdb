package daos;

import domain.Point;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PointDao implements Dao<Point>{

  EntityManager entityManager;

  public PointDao(EntityManager entityManager){
    this.entityManager = entityManager;
  }

  @Override
  public List<Point> getAll(){
    TypedQuery<Point> query =
        entityManager.createQuery("SELECT p FROM domain.Point p", Point.class);
    return query.getResultList();
  }

  @Override
  public void save(Point point) {
    entityManager.getTransaction().begin();
    entityManager.persist(point);
    entityManager.getTransaction().commit();
  }

  @Override
  public void update(Point point)  {
    entityManager.getTransaction().begin();
    point.setX(point.getX() + 100); // update entity
    entityManager.getTransaction().commit();
  }

  @Override
  public void delete(Point point)  {
    entityManager.getTransaction().begin();
    entityManager.remove(point); // delete entity
    entityManager.getTransaction().commit();
  }

  public Long getNum(){
    Query query = entityManager.createQuery("SELECT COUNT(p) FROM domain.Point p");
    return (Long) query.getSingleResult();
  }

  public List<Point> getGreatherOrEqualThan(int number) {
    Query query = entityManager.createQuery(
            "SELECT p FROM domain.Point p WHERE p.x >= :x");
    query.setParameter("x", number);
    return query.getResultList();
  }

  // A EVITAR!!!
  public List<Point> getInyectionOql(String data) {
    // Si data = "0; SELECT gf FROM domain.GeometricFrom gf";
    Query query = entityManager.createQuery(
            "SELECT p FROM domain.Point p WHERE p.x >= " + data);
    return query.getResultList();
  }


}
