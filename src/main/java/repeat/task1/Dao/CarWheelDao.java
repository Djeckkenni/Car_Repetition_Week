package repeat.task1.Dao;

import java.util.List;
import repeat.task1.model.CarWheel;

public interface CarWheelDao {
    CarWheel create(CarWheel carWheel);

    List<CarWheel> getAll();
}
