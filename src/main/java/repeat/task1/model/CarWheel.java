package repeat.task1.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarWheel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double wheelCondition;

    public CarWheel(){

    }

    public CarWheel(double wheelCondition) {
        this.wheelCondition = wheelCondition;
    }

    public double getWheelCondition() {
        return wheelCondition;
    }

    public void setWheelCondition(double wheelCondition) {
        this.wheelCondition = wheelCondition;
    }

    public double changeToNew() {
        wheelCondition = 1.0;
        System.out.println("Wheel was change to new. Wheel condition is " + wheelCondition);
        return wheelCondition;
    }

    public double eraseWheel(int percent) {
        if (percent > 0 && percent < 100) {
            wheelCondition = wheelCondition / 100 * percent;
            System.out.println("Wheel was erase. Wheel condition is " + wheelCondition);
        } else {
            System.out.println("Please enter correct percent!");
        }
        return wheelCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarWheel carWheel = (CarWheel) o;
        return Double.compare(carWheel.wheelCondition, wheelCondition) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wheelCondition);
    }

    @Override
    public String toString() {
        return "CarWheel{" + "wheelCondition=" + wheelCondition + '}';
    }
}
