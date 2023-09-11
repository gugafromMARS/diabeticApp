package mindera.mindswap.personalproject.model.diabeticDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mindera.mindswap.personalproject.model.insulin.Insulin;

import java.util.List;

@Entity(name = "diabeticDetail")
@Table(name = "diabeticDetail")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiabeticDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double insulinPerCarbohydrate;

    private DiabeticType diabeticType;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Insulin> insulinList;

}
