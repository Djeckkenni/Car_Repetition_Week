package repeat.task1.service;

import repeat.task1.model.Car;
import repeat.task1.model.CarDoor;
import repeat.task1.model.CarWheel;

public interface CarService {
    Car create(Car car);

    Car getById(Car car);

    boolean delete(Car car);

    Car update(Car car);

    int changeCurrentSpeed(Car car, int currentSpeedNew);

    int sitPassengers(Car car, int index, CarDoor[] carDoors);

    int leavePassengers(Car car, int index, CarDoor[] carDoors);

    int leaveAllPassenger(Car car, CarDoor[] carDoors);

    CarWheel[] deleteAllWheels(Car car);

    CarWheel[] addNewWheels(Car car);

    double currentMaxSpeed(Car car);
}
