package repeat.task1;

import java.util.List;
import org.junit.Test;
import repeat.task1.lib.Injector;
import repeat.task1.model.Car;
import repeat.task1.model.CarDoor;
import repeat.task1.model.CarWheel;
import repeat.task1.service.CarDoorService;
import repeat.task1.service.CarService;
import repeat.task1.service.CarWheelService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class MainTest {
    private static final Injector INJECTOR = Injector.getInstance("repeat.task1");
    private static final CarService carService =
            (CarService) INJECTOR.getInstance(CarService.class);
    private static final CarDoorService carDoorService =
            (CarDoorService) INJECTOR.getInstance(CarDoorService.class);
    private static final CarWheelService carWheelService =
            (CarWheelService) INJECTOR.getInstance(CarWheelService.class);

    @Test
    public void testMain() {
        CarDoor carDoor1 = new CarDoor("close", "close");
        carDoorService.create(carDoor1);
        CarDoor carDoor2 = new CarDoor("open", "close");
        carDoorService.create(carDoor2);
        CarDoor carDoor3 = new CarDoor("close", "open");
        carDoorService.create(carDoor3);
        CarDoor carDoor4 = new CarDoor("open", "open");
        carDoorService.create(carDoor4);

        List<CarDoor> doors = carDoorService.getAll();
        CarDoor[] carDoors = doors.toArray(new CarDoor[4]);

        CarWheel carWheel1 = new CarWheel(0.2);
        carWheelService.create(carWheel1);
        CarWheel carWheel2 = new CarWheel(0.4);
        carWheelService.create(carWheel2);
        CarWheel carWheel3 = new CarWheel(0.6);
        carWheelService.create(carWheel3);
        CarWheel carWheel4 = new CarWheel(1);
        carWheelService.create(carWheel4);

        List<CarWheel> wheelsList = carWheelService.getAll();
        CarWheel[] wheels = wheelsList.toArray(new CarWheel[4]);

        Car car = Car.newBuilder().setManufactureDate("2014-08-04")
                .setEngineType("omrong").setMaxSpeedNewCar(200)
                .setAccelerationTime(5.0).setPassengerCapacity(4).setDoors(carDoors).
                        setWheels(wheels).setCurrentSpeed(120).setNumberOfPassengers(4).build();

        carService.create(car);
        Long id = car.getId();
        assertTrue(1 == id);
        assertEquals(100, carService.changeCurrentSpeed(car, 100));
        assertEquals(4, carService.sitPassengers(car, 1, carDoors));
        assertEquals(3, carService.leavePassengers(car, 1, carDoors));
        assertEquals(0, carService.leaveAllPassenger(car, carDoors));
        assertEquals(1, carService.sitPassengers(car, 2, carDoors));
        assertArrayEquals(new CarWheel[]{}, carService.deleteAllWheels(car));

        carService.addNewWheels(car);
        carService.addNewWheels(car);
        carService.addNewWheels(car);
        CarWheel[] carWheels = carService.addNewWheels(car);
        CarWheel carWheel = carWheels[3];
        double wheelCondition = carWheel.getWheelCondition();
        assertEquals(1, wheelCondition, 0.01);

        carWheels[0].setWheelCondition(0.2);
        assertEquals(0.2 * 200, carService.currentMaxSpeed(car), 0.01);
    }
}