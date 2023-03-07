package tt.example.test.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tt.example.test.car.model.Car;
import tt.example.test.car.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;


    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).orElseThrow( () -> new NotFoundException("No car with id: " + id));
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car editCar(Car car) {
        return carRepository.save(car);
    }


    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
