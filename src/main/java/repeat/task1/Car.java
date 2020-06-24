package repeat.task1;

import java.time.LocalDate;
import java.util.Arrays;
import repeat.task1.door.CarDoor;
import repeat.task1.door.Condition;
import repeat.task1.wheel.CarWheel;

public class Car {
    private LocalDate manufactureDate;
    private String engineType;
    private double maxSpeedNewCar;
    private double accelerationTime;
    private int passengerCapacity;
    private int numberOfPassengers;
    private int currentSpeed;
    private CarWheel[] wheels;
    private CarDoor[] doors;

    private Car() {

    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public String getEngineType() {
        return engineType;
    }

    public double getMaxSpeedNewCar() {
        return maxSpeedNewCar;
    }

    public double getAccelerationTime() {
        return accelerationTime;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public CarWheel[] getWheels() {
        return wheels;
    }

    public CarDoor[] getDoors() {
        return doors;
    }

    public static Builder newBuilder() {
        return new Car().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setManufactureDate(String manufactureDate) {
            Car.this.manufactureDate = LocalDate.parse(manufactureDate);
            return this;
        }

        public Builder setEngineType(String engineType) {
            Car.this.engineType = engineType;
            return this;
        }

        public Builder setMaxSpeedNewCar(double maxSpeedNewCar) {
            Car.this.maxSpeedNewCar = maxSpeedNewCar;
            return this;
        }

        public Builder setAccelerationTime(double accelerationTime) {
            Car.this.accelerationTime = accelerationTime;
            return this;
        }

        public Builder setPassengerCapacity(int passengerCapacity) {
            Car.this.passengerCapacity = passengerCapacity;
            return this;
        }

        public Builder setNumberOfPassengers(int numberOfPassengers) {
            Car.this.numberOfPassengers = numberOfPassengers;
            return this;
        }

        public Builder setCurrentSpeed(int currentSpeed) {
            Car.this.currentSpeed = currentSpeed;
            return this;
        }

        public Builder setWheels(CarWheel[] wheels) {
            Car.this.wheels = wheels;
            return this;
        }

        public Builder setDoors(CarDoor[] doors) {
            Car.this.doors = doors;
            return this;
        }

        public Car build() {
            return Car.this;
        }

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

    public int changeCurrentSpeed(int currentSpeed) {
        if (currentSpeed > 0) {
            this.currentSpeed = currentSpeed;
            System.out.println("Current speed was change! Current speed is " + currentSpeed);
        } else {
            System.out.println("Speed cant be nagative!");
        }
        return this.currentSpeed;
    }

    public int sitPassengers(int index, CarDoor[] carDoors) {
        if (numberOfPassengers < passengerCapacity) {
            CarDoor door = getDoorByIndex(index, carDoors);
            if (!door.getDoorCondition().equals(Condition.OPEN)) {
                door.open();
            }
            sit(door);
        } else {
            System.out.println("There is no more free place.");
        }
        return numberOfPassengers;
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

    public int leavePassengers(int index, CarDoor[] carDoors) {
        if (numberOfPassengers >= 1) {
            CarDoor door = getDoorByIndex(index, carDoors);
            if (!door.getDoorCondition().equals(Condition.OPEN)) {
                door.open();
            }
            leave(door);
        } else {
            System.out.println("There is no passenger!");
        }
        return numberOfPassengers;
    }

    public int leaveAllPassenger(CarDoor[] carDoors) {
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
        return numberOfPassengers;
    }

    public CarDoor getDoorByIndex(int index, CarDoor[] carDoors) {
        return carDoors[index];
    }

    public CarWheel getWheelByIndex(int index, CarWheel[] carWheels) {
        return carWheels[index];
    }

    public void deleteAllWheels(Car car) {
        car.new Builder().setWheels(new CarWheel[]{});
    }

    public CarWheel[] addNewWheels(Car car) {
        CarWheel[] wheels = car.getWheels();
        CarWheel[] wheelsNew = Arrays.copyOf(wheels, 1 + wheels.length);
        for (int i = wheels.length; i < wheelsNew.length; i++) {
            wheelsNew[i] = new CarWheel(0.5);
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
}

