package tt.example.test.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tt.example.test.car.model.Car;
import tt.example.test.car.service.CarService;
import tt.example.test.car.model.dto.Commission;
import tt.example.test.car.model.dto.CommissionDto;
import tt.example.test.car.service.CommissionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commission")
public class CommissionController {
    private final CommissionService commissionService;
    private final CarService carService;

    @GetMapping
    public List<Commission> getCommissions() {
        return commissionService.getCommissions();
    }

    @GetMapping("/{id}")
    public Commission getCommission(@PathVariable Long id) {
        return commissionService.getCommission(id);
    }

    @GetMapping("/{id}/cars")
    @Transactional(readOnly = true)
    public List<Car> getCarsByCommissionId(@PathVariable Long id) {
        Commission commission = commissionService.getCommission(id);
        return commission.getCars();
    }

    @PostMapping
    public Commission saveCommission(@RequestBody CommissionDto commissionDto) {
        return commissionService.saveCommission(Commission.builder()
                .name(commissionDto.name())
                .city(commissionDto.city())
                .build()
        );
    }

    @PostMapping("/{commissionId}/addCar/{carId}")
    public Commission addCarToCommission(@PathVariable Long commissionId, @PathVariable Long carId) {
        Commission commission = commissionService.getCommission(commissionId);
        Car car = carService.getCar(carId);
        commission.getCars().add(car);
        return commissionService.saveCommission(commission);
    }

    @PutMapping("/{id}")
    public Commission editCommission(@RequestBody CommissionDto commissionDto, @PathVariable Long id) {
        return commissionService.editCommission(Commission.builder()
                .id(id)
                .name(commissionDto.name())
                .city(commissionDto.city())
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCommission(@PathVariable Long id) {
        try {
            commissionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
