package mindera.mindswap.personalproject.appointment.repository;

import mindera.mindswap.personalproject.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM appointment a WHERE a.doctor.id = :doctorId AND a.patient.id = :patientId AND a.localDateTime = :localDateTime")
    Appointment findByDoctorIdPatientIdAndDate(Long doctorId, Long patientId, LocalDate localDateTime);
}
