package repeat.task1;

import java.time.LocalDate;
import java.util.Arrays;
import repeat.task1.door.CarDoor;
import repeat.task1.door.Condition;
import repeat.task1.wheel.CarWheel;

public class Car {
    private final LocalDate manufactureDate;
    private String engineType;
    private double maxSpeedNewCar;
    private double accelerationTime;
    private int passengerCapacity;
    private int numberOfPassengers;
    private int currentSpeed;
    private CarWheel[] wheels;
    private CarDoor[] doors;

    public Car(String manufactureDate, String engineType, double maxSpeedNewCar,
               double accelerationTime, int passengerCapacity, CarWheel[] wheels,
               CarDoor[] doors) {
        this.manufactureDate = LocalDate.parse(manufactureDate);
        this.engineType = engineType;
        this.maxSpeedNewCar = maxSpeedNewCar;
        this.accelerationTime = accelerationTime;
        this.passengerCapacity = passengerCapacity;
        this.wheels = wheels;
        this.doors = doors;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getMaxSpeedNewCar() {
        return maxSpeedNewCar;
    }

    public void setMaxSpeedNewCar(double maxSpeedNewCar) {
        this.maxSpeedNewCar = maxSpeedNewCar;
    }

    public double getAccelerationTime() {
        return accelerationTime;
    }

    public void setAccelerationTime(double accelerationTime) {
        this.accelerationTime = accelerationTime;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public CarWheel[] getWheels() {
        return wheels;
    }

    public void setWheels(CarWheel[] wheels) {
        this.wheels = wheels;
    }

    public CarDoor[] getDoors() {
        return doors;
    }

    public void setDoors(CarDoor[] doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" + "manufactureDate=" + manufactureDate
                + ", engineType='" + engineType + '\''
                + ", maxSpeedNewCar=" + maxSpeedNewCar
                + ", accelerationTime=" + accelerationTime
                + ", passengerCapacity=" + passengerCapacity
                + ", numberOfPassengers=" + numberOfPassengers
                + ", currentSpeed=" + currentSpeed + ", wheels="
                + Arrays.toString(wheels) + ", doors=" + Arrays.toString(doors) + '}';
    }

    public void changeCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
        System.out.println("Current speed was change! Current speed is " + currentSpeed);
    }

    public void sitPassengers(int index, CarDoor[] carDoors) {
        CarDoor door = getDoorByIndex(index, carDoors);
        if (!door.getDoorCondition().equals(Condition.OPEN)) {
            door.open();
        }
        sit(door);
    }

    private void sit(CarDoor door) {
        System.out.println("Passenger is sit down!");
        numberOfPassengers++;
        door.close();
    }

    private void leave(CarDoor door) {
        System.out.println("Passenger leave!");
        numberOfPassengers--;
        door.close();
    }

    public void leavePassengers(int index, CarDoor[] carDoors) {
        CarDoor door = getDoorByIndex(index, carDoors);
        if (!door.getDoorCondition().equals(Condition.OPEN)) {
            door.open();
        }
        leave(door);
    }

    public void leaveAllPassenger(CarDoor[] carDoors) {
        for (int i = 0; i < carDoors.length; i++) {
            if (!carDoors[i].getDoorCondition().equals(Condition.OPEN)) {
                carDoors[i].open();
            }
        }
        numberOfPassengers = 0;
        for (int i = 0; i < carDoors.length; i++) {
            if (!carDoors[i].getDoorCondition().equals(Condition.CLOSE)) {
                carDoors[i].close();
            }
        }
    }

    public CarDoor getDoorByIndex(int index, CarDoor[] carDoors) {
        return carDoors[index];
    }

    public CarWheel getWheelByIndex(int index, CarWheel[] carWheels) {
        return carWheels[index];
    }

    public void deleteAllWheels(Car car) {
        car.setWheels(new CarWheel[]{});
    }

    public CarWheel[] addNewWheels(int index, Car car) {
        CarWheel[] wheels = car.getWheels();
        CarWheel[] wheelsNew = Arrays.copyOf(wheels, index + wheels.length);
        for (int i = wheels.length; i < wheelsNew.length; i++) {
            wheelsNew[i] = new CarWheel(Math.random());
        }
        return wheelsNew;
    }

    public double currentMaxSpeed(Car car) {
        if (numberOfPassengers == 0 && car.getWheels().length >= 4) {
            maxSpeedNewCar = 0;
            return maxSpeedNewCar;
        }
        CarWheel[] wheels = car.getWheels();
        double[] arrCond = new double[wheels.length];
        for (int i = 0; i < arrCond.length; i++) {
            arrCond[i] = wheels[i].getWheelCondition();
        }
        System.out.println(Arrays.toString(arrCond));
        Arrays.sort(arrCond);
        System.out.println(Arrays.toString(arrCond));
        maxSpeedNewCar = maxSpeedNewCar * arrCond[0];
        return maxSpeedNewCar;
    }

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

        Car car = new Car("2015-08-04", "diesel", 250,
                4.0, 4, carWheelsArray, carDoorsArray);
        System.out.println(car.toString());
        car.changeCurrentSpeed(120);
        System.out.println(car.getCurrentSpeed());
        car.sitPassengers(2, carDoorsArray);
        System.out.println(car.getNumberOfPassengers());
        car.leavePassengers(1, carDoorsArray);
        System.out.println(car.getNumberOfPassengers());
        car.sitPassengers(2, carDoorsArray);
        car.sitPassengers(2, carDoorsArray);
        car.sitPassengers(2, carDoorsArray);
        System.out.println(car.getNumberOfPassengers());
        car.leaveAllPassenger(carDoorsArray);
        System.out.println(car.getNumberOfPassengers());
        System.out.println(car.getWheelByIndex(1, carWheelsArray).toString());
        System.out.println(Arrays.toString(car.getWheels()));
        car.deleteAllWheels(car);
        CarWheel[] carWheels = car.addNewWheels(4, car);
        System.out.println(Arrays.toString(carWheels));
        car.setWheels(carWheels);
        System.out.println(car.currentMaxSpeed(car));
        System.out.println(car.toString());
    }
}
