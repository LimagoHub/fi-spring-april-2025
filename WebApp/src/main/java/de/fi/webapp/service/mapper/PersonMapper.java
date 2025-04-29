package de.fi.webapp.service.mapper;


import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.service.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonEntity personEntity);
    PersonEntity convert(Person person);
    Iterable<Person> convert(Iterable<PersonEntity> personEntities);
}
