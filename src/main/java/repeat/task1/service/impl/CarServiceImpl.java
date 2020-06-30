package repeat.task1.service.impl;

import java.util.Arrays;
import repeat.task1.Dao.CarDao;
import repeat.task1.Dao.CarWheelDao;
import repeat.task1.lib.Inject;
import repeat.task1.lib.Service;
import repeat.task1.model.Car;
import repeat.task1.model.CarDoor;
import repeat.task1.model.CarWheel;
import repeat.task1.model.Condition;
import repeat.task1.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private CarDao carDao;
    @Inject
    private CarWheelDao carWheelDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car getById(Car car) {
        return carDao.getById(car);
    }

    @Override
    public boolean delete(Car car) {
        return carDao.delete(car);
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public int changeCurrentSpeed(Car car, int currentSpeedNew) {
        if (currentSpeedNew >= 0) {
            car.setCurrentSpeed(currentSpeedNew);
            update(car);
            System.out.println("Current speed was change! Current speed is "
                    + car.getCurrentSpeed());
        } else {
            System.out.println("Speed cant be nagative!");
        }
        return car.getCurrentSpeed();
    }

    @Override
    public int sitPassengers(Car car, int index, CarDoor[] carDoors) {
        if (car.getNumberOfPassengers() < car.getPassengerCapacity()) {
            CarDoor door = getDoorByIndex(index, carDoors);
            if (!door.getDoorCondition().equals(Condition.OPEN)) {
                door.open();
            }
            sit(door, car);
            update(car);
        } else {
            System.out.println("There is no more free place.");
        }
        return car.getNumberOfPassengers();
    }

    @Override
    public int leavePassengers(Car car, int index, CarDoor[] carDoors) {
        if (car.getNumberOfPassengers() >= 1) {
            CarDoor door = getDoorByIndex(index, carDoors);
            if (!door.getDoorCondition().equals(Condition.OPEN)) {
                door.open();
            }
            leave(door, car);
            update(car);
        } else {
            System.out.println("There is no passenger!");
        }
        return car.getNumberOfPassengers();
    }

    @Override
    public int leaveAllPassenger(Car car, CarDoor[] carDoors) {
        for (int i = 0; i < carDoors.length; i++) {
            if (!carDoors[i].getDoorCondition().equals(Condition.OPEN)) {
                carDoors[i].open();
            }
        }
        car.setNumberOfPassengers(0);
        for (int i = 0; i < carDoors.length; i++) {
            if (!carDoors[i].getDoorCondition().equals(Condition.CLOSE)) {
                carDoors[i].close();
            }
        }
        update(car);
        return car.getNumberOfPassengers();
    }

    @Override
    public CarWheel[] deleteAllWheels(Car car) {
        car.setWheels(new CarWheel[]{});
        update(car);
        return car.getWheels();
    }

    @Override
    public CarWheel[] addNewWheels(Car car) {
        CarWheel[] wheels = car.getWheels();
        if (wheels.length <= 3) {
            CarWheel[] wheelsNew = Arrays.copyOf(wheels, 1 + wheels.length);
            CarWheel carWheel = new CarWheel(1);
            carWheelDao.create(carWheel);
            wheelsNew[wheels.length] = carWheel;
            car.setWheels(wheelsNew);
            update(car);
        } else {
            System.out.println("No need more wheels");
        }
        return car.getWheels();
    }

    @Override
    public double currentMaxSpeed(Car car) {
        if (car.getNumberOfPassengers() == 0 && car.getWheels().length >= 4) {
            car.setMaxSpeedNewCar(0);
            update(car);
            return car.getMaxSpeedNewCar();
        }
        CarWheel[] wheels = car.getWheels();
        double[] arrCond = new double[wheels.length];
        for (int i = 0; i < arrCond.length; i++) {
            arrCond[i] = wheels[i].getWheelCondition();
        }
        System.out.println(Arrays.toString(arrCond));
        Arrays.sort(arrCond);
        System.out.println(Arrays.toString(arrCond));
        car.setMaxSpeedNewCar(car.getMaxSpeedNewCar() * arrCond[0]);
        update(car);
        return car.getMaxSpeedNewCar();
    }

    private CarDoor getDoorByIndex(int index, CarDoor[] carDoors) {
        return carDoors[index];
    }

    private void sit(CarDoor door, Car car) {
        System.out.println("Passenger is sit down!");
        car.setNumberOfPassengers(car.getNumberOfPassengers() + 1);
        door.close();
    }

    private void leave(CarDoor door, Car car) {
        System.out.println("Passenger leave!");
        car.setNumberOfPassengers(car.getNumberOfPassengers() - 1);
        door.close();
    }

    private CarWheel getWheelByIndex(int index, CarWheel[] carWheels) {
        return carWheels[index];
    }
}
