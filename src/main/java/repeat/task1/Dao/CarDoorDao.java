package repeat.task1.Dao;

import java.util.List;
import repeat.task1.model.CarDoor;

public interface CarDoorDao {
    CarDoor create(CarDoor door);

    CarDoor getById(CarDoor door);

    List<CarDoor> getAll();

}
