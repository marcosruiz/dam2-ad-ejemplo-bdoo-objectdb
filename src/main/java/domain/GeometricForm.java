package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
public class GeometricForm {

  @OneToMany(cascade = ALL, targetEntity = Point.class)
  private List<Point> points;

  @Override
  public String toString() {
    String toReturn = "[ ";

    for (Point point : points){
      toReturn += String.format("(%d, %d)", point.getX(), point.getY());
      if(!point.equals(points.get(points.size()-1))){
        toReturn += ", ";
      }
    }

    toReturn += " ]";
    return  toReturn;
  }
}
