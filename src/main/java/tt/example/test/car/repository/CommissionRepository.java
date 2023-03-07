package tt.example.test.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tt.example.test.car.model.dto.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Long> {
}
