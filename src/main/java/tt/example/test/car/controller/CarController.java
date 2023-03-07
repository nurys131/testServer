package tt.example.test.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tt.example.test.aop.Log;
import tt.example.test.car.model.Car;
import tt.example.test.car.model.dto.CarDto;
import tt.example.test.car.service.CarService;
import tt.example.test.car.model.dto.Commission;
import tt.example.test.car.service.CommissionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CommissionService commissionService;

    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

//    @GetMapping("/commission/{id}/cars")
//    @Transactional(readOnly = true)
//    public List<Car> getCarsByCommissionId(@PathVariable Long id) {
//        Commission commission = commissionService.getCommission(id);
//        return commission.getCars();
//    }

    @Log
    @PostMapping
    public Car saveCar(@RequestBody CarDto carDto, @RequestParam(required = false) Long commissionId) {
        Car car = Car.builder()
                .name(carDto.getName())
                .year(carDto.getYear())
                .build();
        carService.saveCar(car);
        if (commissionId != null) {
            Commission commission = commissionService.getCommission(commissionId);
            commission.getCars().add(car);
            commissionService.saveCommission(commission);
        }
        return car;
    }

    @PutMapping("/{id}")
    public Car editCar(@RequestBody CarDto carDto, @PathVariable Long id) {
        return carService.editCar(Car.builder()
                .id(id)
                .name(carDto.getName())
                .year(carDto.getYear())
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        try {
            carService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
