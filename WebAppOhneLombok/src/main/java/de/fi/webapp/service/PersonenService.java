package de.fi.webapp.service;

import de.fi.webapp.service.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonenService {

    void speichern(Person person) throws PersonenServiceException;

    boolean loeschen(UUID id) throws PersonenServiceException;

    Optional<Person> findeAnhandId(UUID id) throws PersonenServiceException;

    Iterable<Person> findeAlle() throws PersonenServiceException;
}
