package mindera.mindswap.personalproject.model.doctor;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mindera.mindswap.personalproject.model.appointment.Appointment;

import java.util.List;

@Entity(name ="doctor")
@Table(name = "doctor")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;
    private String email;

    private String address;

    private SpecialityType speciality;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments;

}
