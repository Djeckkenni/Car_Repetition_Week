package repeat.task1.model;

import java.util.Locale;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarDoor implements Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Condition doorCondition;
    private Condition windowCondition;

    public CarDoor() {
    }

    public CarDoor(String doorCondition, String windowCondition) {
        this.doorCondition = converterEnum(doorCondition);
        this.windowCondition = converterEnum(windowCondition);
    }

    public Long getId() {
        return id;
    }

    private Condition converterEnum(String value) {
        Condition condition = Condition.valueOf(value.toUpperCase(Locale.ENGLISH));
        return condition;
    }

    public Condition getDoorCondition() {
        return doorCondition;
    }

    public void setDoorCondition(Condition doorCondition) {
        this.doorCondition = doorCondition;
    }

    public Condition getWindowCondition() {
        return windowCondition;
    }

    public void setWindowCondition(Condition windowCondition) {
        this.windowCondition = windowCondition;
    }

    @Override
    public Condition open() {
        if (doorCondition.equals(Condition.OPEN)) {
            return close();
        }
        System.out.println("Door open!");
        doorCondition = Condition.OPEN;
        return doorCondition;
    }

    @Override
    public Condition close() {
        if (doorCondition.equals(Condition.CLOSE)) {
            return open();
        }
        System.out.println("Door close!");
        doorCondition = Condition.CLOSE;
        return doorCondition;
    }

    public class DoorWindow implements Action {
        @Override
        public Condition open() {
            if (windowCondition.equals(Condition.OPEN)) {
                return close();
            }
            System.out.println("Window open!");
            windowCondition = Condition.OPEN;
            return windowCondition;
        }

        @Override
        public Condition close() {
            if (windowCondition.equals(Condition.CLOSE)) {
                return open();
            }
            System.out.println("Window close!");
            windowCondition = Condition.CLOSE;
            return windowCondition;
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
        CarDoor carDoor = (CarDoor) o;
        return doorCondition == carDoor.doorCondition
                && windowCondition == carDoor.windowCondition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doorCondition, windowCondition);
    }

    @Override
    public String toString() {
        return "CarDoor{" + "doorCondition="
                + doorCondition + ", windowCondition="
                + windowCondition + '}';
    }
}
