package mindera.mindswap.personalproject.register.model;


import jakarta.persistence.*;
import lombok.*;
import mindera.mindswap.personalproject.patient.model.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "register")
@Table(name = "register")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int glucose;
    private LocalDateTime localDateTime;
    private double carboHydrates;
    private double insulin;
    @ManyToOne
    private Patient patient;

}
