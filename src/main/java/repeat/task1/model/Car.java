package repeat.task1.model;

import java.time.LocalDate;
import java.util.Arrays;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate manufactureDate;
    private String engineType;
    private double maxSpeedNewCar;
    private double accelerationTime;
    private int passengerCapacity;
    private int numberOfPassengers;
    private int currentSpeed;
    @OneToMany
    @OrderColumn
    private CarWheel[] wheels;

    @OneToMany
    @OrderColumn
    private CarDoor[] doors;

    protected Car() {

    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public void setMaxSpeedNewCar(double maxSpeedNewCar) {
        this.maxSpeedNewCar = maxSpeedNewCar;
    }

    public void setAccelerationTime(double accelerationTime) {
        this.accelerationTime = accelerationTime;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setWheels(CarWheel[] wheels) {
        this.wheels = wheels;
    }

    public void setDoors(CarDoor[] doors) {
        this.doors = doors;
    }

    public Long getId() {
        return id;
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
        return "Car{" + "id=" + id + ", manufactureDate="
                + manufactureDate + ", engineType='" + engineType
                + '\'' + ", maxSpeedNewCar=" + maxSpeedNewCar
                + ", accelerationTime=" + accelerationTime + ", passengerCapacity="
                + passengerCapacity + ", numberOfPassengers=" + numberOfPassengers
                + ", currentSpeed=" + currentSpeed + ", wheels=" + Arrays.toString(wheels)
                + ", doors=" + Arrays.toString(doors) + '}';
    }
}



