package repeat.task1;

import java.util.List;
import repeat.task1.lib.Injector;
import repeat.task1.model.Car;
import repeat.task1.model.CarDoor;
import repeat.task1.model.CarWheel;
import repeat.task1.service.CarDoorService;
import repeat.task1.service.CarService;
import repeat.task1.service.CarWheelService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("repeat.task1");
    private static final CarService carService =
            (CarService) INJECTOR.getInstance(CarService.class);
    private static final CarDoorService carDoorService =
            (CarDoorService) INJECTOR.getInstance(CarDoorService.class);
    private static final CarWheelService carWheelService =
            (CarWheelService) INJECTOR.getInstance(CarWheelService.class);

    public static void main(String[] args) {
        CarDoor carDoor1 = new CarDoor("close", "close");
        carDoorService.create(carDoor1);
        CarDoor carDoor2 = new CarDoor("open", "close");
        carDoorService.create(carDoor2);
        CarDoor carDoor3 = new CarDoor("close", "open");
        carDoorService.create(carDoor3);
        CarDoor carDoor4 = new CarDoor("open", "open");
        carDoorService.create(carDoor4);
        List<CarDoor> all = carDoorService.getAll();
        CarDoor[] doors = all.toArray(new CarDoor[4]);

        CarWheel carWheel1 = new CarWheel(Math.random());
        carWheelService.create(carWheel1);
        CarWheel carWheel2 = new CarWheel(Math.random());
        carWheelService.create(carWheel2);
        CarWheel carWheel3 = new CarWheel(Math.random());
        carWheelService.create(carWheel3);
        CarWheel carWheel4 = new CarWheel(Math.random());
        carWheelService.create(carWheel4);
        List<CarWheel> all1 = carWheelService.getAll();
        CarWheel[] carWheels = all1.toArray(new CarWheel[4]);

        Car car = Car.newBuilder().setManufactureDate("2015-08-04")
                .setEngineType("diesel").setMaxSpeedNewCar(250)
                .setAccelerationTime(4.0).setPassengerCapacity(4).setDoors(doors)
                        .setWheels(carWheels).setCurrentSpeed(120).setNumberOfPassengers(4).build();

        carService.create(car);
        carService.changeCurrentSpeed(car, 100);
        carService.sitPassengers(car, 2, doors);
        carService.leavePassengers(car, 1, doors);
        carService.leaveAllPassenger(car, doors);
        carService.deleteAllWheels(car);
        carService.sitPassengers(car, 1, doors);
        carService.addNewWheels(car);
        carService.addNewWheels(car);
        carService.addNewWheels(car);
        carService.addNewWheels(car);
        carService.currentMaxSpeed(car);
    }
}
