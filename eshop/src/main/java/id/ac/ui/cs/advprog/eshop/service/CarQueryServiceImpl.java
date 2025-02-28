package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarQueryServiceImpl implements CarQueryService {
    @Autowired
    private CarReadRepository carReadRepository;  // Gunakan interface yang sesuai

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = (Iterator<Car>) carReadRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        return carReadRepository.findById(carId);
    }
}
