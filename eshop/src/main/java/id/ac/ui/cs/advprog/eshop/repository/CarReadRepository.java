package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarReadRepository {
    Iterable<Car> findAll();
    Car findById(String id);
}
