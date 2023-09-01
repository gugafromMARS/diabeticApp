package mindera.mindswap.personalproject.model.doctor;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.appointment.Appointment;

import java.util.List;

@Entity(name ="doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;
    private String address;
    private SpecialityType speciality;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String habitation) {
        this.address = habitation;
    }

    public SpecialityType getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityType speciality) {
        this.speciality = speciality;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public static DoctorBuilder builder(){
        return new DoctorBuilder();
    }

    public static class DoctorBuilder {

        private Doctor doctor;

        public DoctorBuilder() {
            doctor = new Doctor();
        }

        public DoctorBuilder withName(String name){
            doctor.setName(name);
            return this;
        }
        public DoctorBuilder withAge(int age){
            doctor.setAge(age);
            return this;
        }

        public DoctorBuilder withEmail(String email){
            doctor.setEmail(email);
            return this;
        }
        public DoctorBuilder withAddress(String address){
            doctor.setAddress(address);
            return this;
        }

        public DoctorBuilder withSpeciality(SpecialityType speciality){
            doctor.setSpeciality(speciality);
            return this;
        }

        public DoctorBuilder withAppointments(List<Appointment> appointments){
            doctor.setAppointments(appointments);
            return this;
        }

        public Doctor build(){
            return doctor;
        }

    }
}
