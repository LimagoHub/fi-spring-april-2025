package de.fi.webapp.presention.mapper;


import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.presention.dto.PersonDTO;
import de.fi.webapp.service.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {

    PersonDTO convert(PersonEntity personEntity);
    Person convert(PersonDTO personDTO);
    Iterable<PersonDTO> convert(Iterable<Person> persons);
}
