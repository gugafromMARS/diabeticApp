package mindera.mindswap.personalproject.model.patient;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.model.register.Register;

import java.util.List;

@Entity(name = "patient")
@Table(name = "patient")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;

    private double height;

    private int weight;

    @OneToOne(cascade = CascadeType.ALL)
    private DiabeticDetails diabeticDetails;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Register> registerList;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments;

}
