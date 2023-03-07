package tt.example.test.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tt.example.test.car.model.dto.Commission;
import tt.example.test.car.repository.CommissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionService {
    private final CommissionRepository commissionRepository;

    public List<Commission> getCommissions() {
        return commissionRepository.findAll();
    }


    public Commission getCommission(Long id) {
        return commissionRepository.findById(id).orElseThrow( () -> new NotFoundException("Cannot found commission with id: " + id));
    }

    public Commission saveCommission(Commission commission) {
        return commissionRepository.save(commission);
    }

    public Commission editCommission(Commission commission) {
        return commissionRepository.save(commission);
    }

    public void deleteById(Long id) {
        commissionRepository.deleteById(id);
    }
}
