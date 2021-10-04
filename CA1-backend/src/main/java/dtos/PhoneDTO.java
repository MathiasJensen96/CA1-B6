package dtos;

import entities.Phone;

import java.util.Objects;

public class PhoneDTO {
    private long id;
    private String number;
    private String description;

    public PhoneDTO(Phone p) {
        this.number = p.getNumber();
        this.description = p.getDescription();
    }

    public PhoneDTO(String n, String desc) {
        this.number = n;
        this.description = desc;
    }

    public PhoneDTO(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDTO phoneDTO = (PhoneDTO) o;
        return id == phoneDTO.id && number.equals(phoneDTO.number) && description.equals(phoneDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, description);
    }
}
