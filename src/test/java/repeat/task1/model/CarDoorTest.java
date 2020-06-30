package repeat.task1.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarDoorTest {

    @Test
    public void testOpenAndCloseDoorCondOpen() {
        CarDoor carDoor = new CarDoor();
        carDoor.setDoorCondition(Condition.OPEN);
        assertEquals(Condition.CLOSE, carDoor.open());
        carDoor.setDoorCondition(Condition.OPEN);
        assertEquals(Condition.CLOSE, carDoor.close());
    }

    @Test
    public void testOpenAndCloseDoorCondClose() {
        CarDoor carDoor = new CarDoor();
        carDoor.setDoorCondition(Condition.CLOSE);
        assertEquals(Condition.OPEN, carDoor.open());
        carDoor.setDoorCondition(Condition.CLOSE);
        assertEquals(Condition.OPEN, carDoor.close());
    }
}