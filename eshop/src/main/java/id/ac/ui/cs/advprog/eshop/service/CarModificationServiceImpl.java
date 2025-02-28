package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarWriteRepository;
import id.ac.ui.cs.advprog.eshop.repository.CarDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarModificationServiceImpl implements CarModificationService {
    @Autowired
    private CarWriteRepository carWriteRepository;

    @Autowired
    private CarDeleteRepository carDeleteRepository;

    @Override
    public Car create(Car car) {
        return carWriteRepository.create(car);
    }

    @Override
    public void update(String carId, Car car) {
        carWriteRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        carDeleteRepository.deleteById(carId);
    }
}
