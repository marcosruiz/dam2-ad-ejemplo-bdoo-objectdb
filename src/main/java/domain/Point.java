package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Point {
  // Persistent Fields:
  private int x;
  private int y;

  // Constructor:
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // String Representation:
  @Override
  public String toString() {
    return String.format("(%d, %d)", getX(), getY());
  }
}