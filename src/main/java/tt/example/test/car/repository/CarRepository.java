package tt.example.test.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tt.example.test.car.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}