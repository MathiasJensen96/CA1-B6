package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;

public interface IPersonFacade {
    PersonDTO addPerson(String fName, String lName, String phone) throws Exception;

    PersonDTO deletePerson(Long id) throws Exception;

    PersonDTO getPerson(long id) throws Exception;

    PersonsDTO getAllPersons();

    PersonDTO editPerson(PersonDTO p) throws Exception;
}

