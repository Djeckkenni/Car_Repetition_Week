package repeat.task1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repeat.task1.door.CarDoor;
import repeat.task1.wheel.CarWheel;

public class CarTest {
    private Car car;

    @Before
    public void unitTest() {
        CarWheel[] carWheelsArray = new CarWheel[4];
        carWheelsArray[0] = new CarWheel(1);
        carWheelsArray[1] = new CarWheel(0.5);
        carWheelsArray[2] = new CarWheel(0.6);
        carWheelsArray[3] = new CarWheel(0.7);

        CarDoor[] carDoorsArray = new CarDoor[4];
        carDoorsArray[0] = new CarDoor("close", "close");
        carDoorsArray[1] = new CarDoor("close", "close");
        carDoorsArray[2] = new CarDoor("open", "open");
        carDoorsArray[3] = new CarDoor("close", "close");
        car = Car.newBuilder().setManufactureDate("2015-08-04")
                .setEngineType("diesel").setMaxSpeedNewCar(250)
                .setAccelerationTime(4.0).setPassengerCapacity(2)
                .setWheels(carWheelsArray).setDoors(carDoorsArray).build();
    }

    @Test()
    public void changeCurrentSpeedTest() {
        Assert.assertEquals(120, car.changeCurrentSpeed(120));
    }

    @Test()
    public void changeCurrentSpeedWithNegativeTest() {
        Assert.assertNotEquals(-1, car.changeCurrentSpeed(car.getCurrentSpeed()));
    }

    @Test
    public void sitPassengersTest() {
        Assert.assertEquals(car.getNumberOfPassengers() + 1, car.sitPassengers(1, car.getDoors()));
    }

    @Test
    public void sitPassengersMoreTest() {
        car.sitPassengers(1, car.getDoors());
        car.sitPassengers(1, car.getDoors());
        car.sitPassengers(1, car.getDoors());
        car.sitPassengers(1, car.getDoors());
        car.sitPassengers(1, car.getDoors());
        Assert.assertEquals(car.getNumberOfPassengers(), car.sitPassengers(1, car.getDoors()));
    }

    @Test
    public void leavePassengersNotTest() {
        Assert.assertNotEquals(car.getNumberOfPassengers() - 1, car.leavePassengers(2, car.getDoors()));
    }

    @Test
    public void leavePassengersTest() {
        car.sitPassengers(1, car.getDoors());
        Assert.assertEquals(car.getNumberOfPassengers() - 1, car.leavePassengers(2, car.getDoors()));
    }

    @Test
    public void leaveAllPassengerTest() {
        Assert.assertEquals(0, car.leaveAllPassenger(car.getDoors()));
    }

    @Test
    public void getDoorByIndexTest() {
        CarDoor carDoor = new CarDoor("open", "open");
        Assert.assertEquals(carDoor, car.getDoorByIndex(2, car.getDoors()));
    }

    @Test
    public void getWheelByIndexTest() {
        CarWheel carWheel = new CarWheel(1);
        Assert.assertEquals(carWheel, car.getWheelByIndex(0, car.getWheels()));
    }

    @Test
    public void addNewWheelsTest() {
        car.deleteAllWheels(car);
        CarWheel[] arr = new CarWheel[1];
        arr[0] = new CarWheel(0.5);
        Assert.assertNotEquals(arr, car.addNewWheels(car));
    }

    @Test
    public void currentMaxSpeedTest() {
        car.sitPassengers(1, car.getDoors());
        car.addNewWheels(car);
        car.addNewWheels(car);
        car.addNewWheels(car);
        car.addNewWheels(car);
        Assert.assertEquals(125.0, car.currentMaxSpeed(car), car.currentMaxSpeed(car));
    }
}