import daos.GeometricFormDao;
import daos.PointDao;
import domain.Point;

import javax.persistence.*;
import java.util.List;

public class MainPoint {
  static EntityManager entityManager;
  static EntityManagerFactory entityManagerFactory;
  static PointDao pointDao;
  static GeometricFormDao geometricFormDao;

  public static void main(String[] args){

    // Establecemos la conexión
    entityManagerFactory =
        Persistence.createEntityManagerFactory(
                "objectdb:$objectdb/db/points.odb");

    entityManager = entityManagerFactory.createEntityManager();

    // Instanciamos los DAOs
    pointDao = new PointDao(entityManager);
    geometricFormDao = new GeometricFormDao(entityManager);

    executionExample();

    // Cerramos la conexión
    entityManager.close();
    entityManagerFactory.close();
  }

  // Ejemplo de ejecución
  public static void executionExample(){
    // Creamos y guardamos 10 points
    for (int i = 0; i < 10; i++) {
      Point point = new Point(i, i);
      pointDao.save(point);
    }

    // Mostramos por pantalla el número de points que hay
    Long numPoints = pointDao.getNum();
    System.out.println("TOTAL POINTS : " + numPoints);

    // Obtenemos y mostramos todos los points
    System.out.println("\nPOINTS:");
    List<Point> points = pointDao.getAll();
    for(Point point : points){
      System.out.println(point);
    }

    // Actualizamos y mostramos todos los points
    System.out.println("\nPOINTS ACTUALIZADOS:");
    for(Point point : points){
      pointDao.update(point);
      System.out.println(point);
    }

    // Hacemos una query personalizada
    System.out.println("\nPOINTS FILTRADOS:");
    List<Point> filteredPoints = pointDao.getGreatherOrEqualXThan(105);
    for(Point point : filteredPoints){
      System.out.println(point);
    }
  }

}
