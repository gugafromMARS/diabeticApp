package mindera.mindswap.personalproject.model.insulin;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Insulin")
public class Insulin {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private InsulinType insulinType;

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

    public InsulinType getInsulinType() {
        return insulinType;
    }

    public void setInsulinType(InsulinType insulinType) {
        this.insulinType = insulinType;
    }
}
