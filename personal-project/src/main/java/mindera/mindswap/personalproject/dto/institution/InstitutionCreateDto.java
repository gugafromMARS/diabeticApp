package mindera.mindswap.personalproject.dto.institution;

import mindera.mindswap.personalproject.model.institution.InstitutionType;

public class InstitutionCreateDto {

    private String name;
    private String address;
    private String email;
    private InstitutionType institutionType;

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

    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(InstitutionType institutionType) {
        this.institutionType = institutionType;
    }

}
