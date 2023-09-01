package mindera.mindswap.personalproject.dto.institution;

import mindera.mindswap.personalproject.model.institution.InstitutionType;

public class InstitutionDto {

    private Long id;
    private String name;
    private String address;
    private String email;
    private InstitutionType type;
    public Long getId() {
        return id;
    }

    public InstitutionDto(Long id, String name, String address, String email, InstitutionType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }
}
