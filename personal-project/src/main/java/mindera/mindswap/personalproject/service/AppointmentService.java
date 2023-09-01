package mindera.mindswap.personalproject.service;


import mindera.mindswap.personalproject.converter.AppointmentConverter;
import mindera.mindswap.personalproject.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    private AppointmentConverter appointmentConverter;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentConverter appointmentConverter) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentConverter = appointmentConverter;
    }


}
