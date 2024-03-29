package entities;

import dtos.PersonDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
@Table (name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Person() {
    }
    private String firstName;
    private String lastName;
    private String email;



    @ManyToMany(mappedBy = "persons", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Hobby> hobbies;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Phone> phones;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        phones = new ArrayList<>();
        hobbies = new ArrayList<>();
    }

    public Person updateFromDto(PersonDTO pDto){
        this.firstName = pDto.getFirstName();
        this.lastName = pDto.getLastName();
        this.email = pDto.getEmail();
        return this;
    }

    public void addPhone(Phone phone) {
        if (phone != null) {
            if (!phones.contains(phone)) {
                this.phones.add(phone);
                phone.setPerson(this);
            } else {
                //TODO: throw exception - this phone has already been added
            }
        }
    }

    public void addHobby(Hobby hobby) {
        if (hobby != null) {
            if (!hobbies.contains(hobby)) {
                this.hobbies.add(hobby);
                hobby.getPersons().add(this);
            } else {
                hobby.getPersons().add(this);
            }
        }
    }

    public void removeHobby(Hobby hobby) {
        if (hobby != null) {
            hobbies.remove(hobby);
            hobby.getPersons().remove(this);
        }
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hobbies=" + hobbies +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}