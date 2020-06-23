package repeat.task1.wheel;

import java.util.Objects;

public class CarWheel {
    private double wheelCondition;

    public CarWheel(double wheelCondition) {
        this.wheelCondition = wheelCondition;
    }

    public double getWheelCondition() {
        return wheelCondition;
    }

    public void setWheelCondition(double wheelCondition) {
        this.wheelCondition = wheelCondition;
    }

    public void changeToNew() {
        wheelCondition = 1.0;
        System.out.println("Wheel was change to new. Wheel condition is " + wheelCondition);
    }

    public void eraseWheel(int percent) {
        if (percent > 0 && percent < 100) {
            wheelCondition = wheelCondition / 100 * percent;
            System.out.println("Wheel was erase. Wheel condition is " + wheelCondition);
        } else {
            System.out.println("Please enter correct percent!");
        }
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
