package repeat.task1.service;

import java.util.List;
import repeat.task1.model.CarWheel;

public interface CarWheelService {
    CarWheel create(CarWheel carWheel);

    List<CarWheel> getAll();
}
