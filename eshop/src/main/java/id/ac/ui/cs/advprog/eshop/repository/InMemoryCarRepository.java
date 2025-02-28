package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryCarRepository implements CarReadRepository, CarWriteRepository, CarDeleteRepository {
    private List<Car> carData = new ArrayList<>();

    @Override
    public Car create(Car car) {
        UUID uuid = UUID.randomUUID();
        car.setCarId(uuid.toString());
        carData.add(car);
        return car;
    }

    @Override
    public Iterable<Car> findAll() { return carData; }

    @Override
    public Car findById(String id) {
        return carData.stream()
                .filter(car -> car.getCarId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Car update(String id, Car updatedCar) {
        for (int i = 0; i < carData.size(); i++) {
            Car car = carData.get(i);
            if (car.getCarId().equals(id)) {
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
