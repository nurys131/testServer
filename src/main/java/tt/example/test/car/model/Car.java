package tt.example.test.car.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tt.example.test.car.model.dto.Commission;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int year;

    @ManyToOne
    @JoinColumn(name = "commission_id")
    private Commission commission;
}
