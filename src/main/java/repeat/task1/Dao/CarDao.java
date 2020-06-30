package repeat.task1.Dao;

import repeat.task1.model.Car;

public interface CarDao {
    Car create(Car car);

    Car getById(Car car);

    boolean delete(Car car);

    Car update(Car car);

}
