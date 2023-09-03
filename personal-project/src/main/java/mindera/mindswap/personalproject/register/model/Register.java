package mindera.mindswap.personalproject.register.model;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.patient.model.Patient;

import java.time.LocalDateTime;

@Entity(name = "register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int glucose;
    private LocalDateTime date;
    private int carboHydrates;
    private int insulin;
    @ManyToOne
    private Patient patient;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGlucose() {
        return glucose;
    }

    public void setGlucose(int glucose) {
        this.glucose = glucose;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCarboHydrates() {
        return carboHydrates;
    }

    public void setCarboHydrates(int carboHydrates) {
        this.carboHydrates = carboHydrates;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    public Patient getUser() {
        return patient;
    }

    public void setUser(Patient patient) {
        this.patient = patient;
    }

    public static RegisterBuilder builder(){
        return new RegisterBuilder();
    }

    public static class RegisterBuilder {
        private Register register;

        public RegisterBuilder() {
            register = new Register();
        }

        public RegisterBuilder withGlucose(int glucose){
            register.setGlucose(glucose);
            return this;
        }

        public RegisterBuilder withDate(LocalDateTime localDateTime){
            register.setDate(localDateTime);
            return this;
        }

        public RegisterBuilder withCarboHydrates(int carboHydrates){
            register.setCarboHydrates(carboHydrates);
            return this;
        }

        public RegisterBuilder withInsulin(int insulin){
            register.setInsulin(insulin);
            return this;
        }

        public RegisterBuilder withUser(Patient patient){
            register.setUser(patient);
            return this;
        }

        public Register build(){
            return register;
        }
    }


}
