package repeat.task1;

import repeat.task1.door.CarDoor;
import repeat.task1.wheel.CarWheel;

public class Main {
    public static void main(String[] args) {
        CarWheel[] carWheelsArray = new CarWheel[4];
        carWheelsArray[0] = new CarWheel(Math.random());
        carWheelsArray[1] = new CarWheel(Math.random());
        carWheelsArray[2] = new CarWheel(Math.random());
        carWheelsArray[3] = new CarWheel(Math.random());

        CarDoor[] carDoorsArray = new CarDoor[4];
        carDoorsArray[0] = new CarDoor("close", "close");
        carDoorsArray[1] = new CarDoor("close", "close");
        carDoorsArray[2] = new CarDoor("close", "close");
        carDoorsArray[3] = new CarDoor("close", "close");

        Car car = Car.newBuilder().setManufactureDate("2015-08-04")
                .setEngineType("diesel").setMaxSpeedNewCar(250)
                .setAccelerationTime(4.0).setPassengerCapacity(2)
                .setWheels(carWheelsArray).setDoors(carDoorsArray).build();

        car.changeCurrentSpeed(120);
        car.sitPassengers(2, carDoorsArray);
        car.sitPassengers(2, carDoorsArray);
        car.addNewWheels(car);
        car.addNewWheels(car);
        car.addNewWheels(car);
        car.addNewWheels(car);
        car.currentMaxSpeed(car);
        System.out.println(car.toString());
    }
}
