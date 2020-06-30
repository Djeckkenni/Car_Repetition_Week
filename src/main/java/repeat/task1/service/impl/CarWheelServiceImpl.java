package repeat.task1.service.impl;

import java.util.List;
import repeat.task1.Dao.CarWheelDao;
import repeat.task1.lib.Inject;
import repeat.task1.lib.Service;
import repeat.task1.model.CarWheel;
import repeat.task1.service.CarWheelService;

@Service
public class CarWheelServiceImpl implements CarWheelService {
    @Inject
    private CarWheelDao carWheelDao;

    @Override
    public CarWheel create(CarWheel wheel) {
        return carWheelDao.create(wheel);
    }

    @Override
    public List<CarWheel> getAll() {
        return carWheelDao.getAll();
    }
}
