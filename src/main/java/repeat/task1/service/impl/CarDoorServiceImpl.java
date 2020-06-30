package repeat.task1.service.impl;

import java.util.List;
import repeat.task1.Dao.CarDoorDao;
import repeat.task1.lib.Inject;
import repeat.task1.lib.Service;
import repeat.task1.model.CarDoor;
import repeat.task1.service.CarDoorService;

@Service
public class CarDoorServiceImpl implements CarDoorService {
    @Inject
    private CarDoorDao carDoorDao;

    @Override
    public CarDoor create(CarDoor door) {
        return carDoorDao.create(door);
    }

    @Override
    public CarDoor getById(CarDoor carDoor) {
        return carDoorDao.getById(carDoor);
    }

    @Override
    public List<CarDoor> getAll() {
        return carDoorDao.getAll();
    }
}
