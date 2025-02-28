package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarRepositoryInterface {
    Car create(Car car);
    Iterable<Car> findAll();
    Car findById(String id);
    Car update(String id, Car updatedCar);
    void deleteById(String id);
}
