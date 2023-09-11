package mindera.mindswap.personalproject.repository.appointment;

import mindera.mindswap.personalproject.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM appointment a WHERE a.doctor.id = :doctorId AND a.patient.id = :patientId AND a.localDate = :localDate")
    Appointment findByDoctorIdPatientIdAndDate(Long doctorId, Long patientId, LocalDate localDate);
}
