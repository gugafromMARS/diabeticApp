package mindera.mindswap.personalproject.dto.doctor;

import mindera.mindswap.personalproject.model.doctor.SpecialityType;

public class DoctorDto {

    private Long id;
    private String name;
    private int age;
    private String email;
    private SpecialityType speciality;

    public DoctorDto(Long id, String name, int age, String email, SpecialityType speciality) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.speciality = speciality;
    }

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

    public SpecialityType getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityType speciality) {
        this.speciality = speciality;
    }
}
