package tt.example.test.car.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tt.example.test.car.model.Car;

import java.util.List;

@Entity
@Table(name = "commission")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "commission_id")
    private List<Car> cars;
}
