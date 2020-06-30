package repeat.task1.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarWheelTest {

    @Test
    public void testChangeToNew() {
        CarWheel carWheel = new CarWheel();
        assertEquals(1, carWheel.changeToNew(), 0.01);
    }

    @Test
    public void testEraseWheelOk() {
        CarWheel carWheel = new CarWheel();
        carWheel.setWheelCondition(1);
        assertEquals(0.5,carWheel.eraseWheel(50), 0.01);
        carWheel.setWheelCondition(1);
        assertNotEquals(0.5, carWheel.eraseWheel(200), 0.01);
        carWheel.setWheelCondition(1);
        assertNotEquals(0.5, carWheel.eraseWheel(-50), 0.01);
    }
}