package repeat.task1.service;

import java.util.List;
import repeat.task1.model.CarDoor;

public interface CarDoorService {
    CarDoor create(CarDoor door);

    CarDoor getById(CarDoor carDoor);

    List<CarDoor> getAll();
}
