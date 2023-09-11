package mindera.mindswap.personalproject.model.appointment;


import jakarta.persistence.*;
import lombok.*;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.patient.Patient;

import java.time.LocalDate;

@Entity(name = "appointment")
@Table(name = "appointment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String description;

    }
