package dtos;

import entities.Hobby;

import java.util.Objects;

public class HobbyDTO {
    private long id;
    private String name;
    private String description;

    public HobbyDTO(Hobby h) {
        this.name = h.getName();
        this.description = h.getDescription();
    }

    public HobbyDTO(String n, String desc) {
        this.name = n;
        this.description = desc;
    }

    public HobbyDTO(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        HobbyDTO hobbyDTO = (HobbyDTO) o;
        return id == hobbyDTO.id && name.equals(hobbyDTO.name) && description.equals(hobbyDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
