package repeat.task1.door;

import java.util.Locale;

public class CarDoor implements Action {
    private Condition doorCondition;
    private Condition windowCondition;

    public CarDoor(String doorCondition, String windowCondition) {
        this.doorCondition = converterEnum(doorCondition);
        this.windowCondition = converterEnum(windowCondition);
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
    public String toString() {
        return "CarDoor{" + "doorCondition="
                + doorCondition + ", windowCondition="
                + windowCondition + '}';
    }
}
