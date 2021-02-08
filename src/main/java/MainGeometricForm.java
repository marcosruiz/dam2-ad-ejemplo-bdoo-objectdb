import daos.GeometricFormDao;
import daos.PointDao;
import domain.GeometricForm;
import domain.Point;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class MainGeometricForm {

  static EntityManager entityManager;
  static EntityManagerFactory entityManagerFactory;
  static PointDao pointDao;
  static GeometricFormDao geometricFormDao;

  public static void main(String[] args){

    // Establecemos la conexión
    entityManagerFactory =
        Persistence.createEntityManagerFactory(
            "points.odb");

    entityManager = entityManagerFactory.createEntityManager();

    // Instanciamos los DAOs
    pointDao = new PointDao(entityManager);
    geometricFormDao = new GeometricFormDao(entityManager);

    executionExample();

    // Cerramos la conexión
    entityManager.close();
    entityManagerFactory.close();
  }

  public static void executionExample(){
    List<Point> points = new ArrayList<>();
    for(int i=0; i<10; i++){
      points.add(new Point(i,+i));
    }

    // Creamos una Forma Geometrica
    GeometricForm geometricForm = new GeometricForm();
    geometricForm.setPoints(points);
    geometricFormDao.save(geometricForm);

    // Mostramos por pantalla todas las formas geométricas
    System.out.println("\nFORMAS GEOMETRICAS: ");
    List<GeometricForm> geometricForms = geometricFormDao.getAll();
    for(GeometricForm gf : geometricForms){
      System.out.println(gf);
    }

    // Mostramos por pantalla todos los puntos
    System.out.println("\nPOINTS TRAS BORRAR FORMA GEOMETRICA:");
    points = pointDao.getAll();
    for(Point point : points){
      System.out.println(point);
    }

    // Eliminamos todas las formas geometricas
    for(GeometricForm gf : geometricForms){
      geometricFormDao.delete(gf);
    }

    // Listamos los puntos
    System.out.println("\nPOINTS TRAS BORRAR FORMA GEOMETRICA:");
    points = pointDao.getAll();
    for(Point point : points){
      System.out.println(point);
    }

    // Elimintamos todos los points
    //for(Point point : points){
    //  pointDao.delete(point);
    //}
  }


}
