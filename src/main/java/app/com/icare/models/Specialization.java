package app.com.icare.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @Column(name = "specialization_id", nullable = false)
    private Integer id;

    @Column(name = "specialization_name", nullable = false, length = 100)
    private String specializationName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}