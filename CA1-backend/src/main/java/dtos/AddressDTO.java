package dtos;

import entities.Address;

import java.util.Objects;

public class AddressDTO {
    private long id;
    private String street;
    private String additionalInfo;

    public AddressDTO(Address a) {
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
    }

    public AddressDTO(String s, String add) {
        this.street = s;
        this.additionalInfo = add;
    }

    public AddressDTO(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return id == that.id && street.equals(that.street) && additionalInfo.equals(that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, additionalInfo);
    }
}
