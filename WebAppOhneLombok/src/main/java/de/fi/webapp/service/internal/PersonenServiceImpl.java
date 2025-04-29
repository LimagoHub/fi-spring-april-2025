package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.BlacklistService;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import de.fi.webapp.service.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
//@Service
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper ;
    private final List<String> antipathen;
    //private final BlacklistService blacklistService;


    public PersonenServiceImpl(final PersonenRepository repo, final PersonMapper mapper, final @Qualifier("blacklist") List<String> antipathen) {
        this.repo = repo;
        this.mapper = mapper;
        this.antipathen = antipathen;
    }

    /*
                       Parameter null -> PSE
                       vorname null -> PSE
                       vorname zu kurz -> PSE
                       nachname null -> PSE
                       nachname zu kurz -> PSE
                       Attila -> PSE
                       runtimeException -> PSE
                       happy day -> person wird an repo übergeben
                    */
    @Override
    public void speichern(final Person person) throws PersonenServiceException {
        try {
            if(person == null)
                throw new PersonenServiceException("Parameter darf nicht null sein");

            if(person.getVorname() == null || person.getVorname().length() < 2)
                throw new PersonenServiceException("Vorname darf nicht null sein und muss mindestens 2 Zeichen lang sein");

            if(person.getNachname() == null || person.getNachname().length() < 2)
                throw new PersonenServiceException("Nachname darf nicht null sein und muss mindestens 2 Zeichen lang sein");

            if(antipathen.contains(person.getVorname() ))
                throw new PersonenServiceException("Antipath");

            repo.save(mapper.convert(person));
        } catch (final RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern", e);
        }
    }

    @Override
    public boolean loeschen(final UUID id) throws PersonenServiceException {
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (final RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Loeschen", e);
        }
    }

    @Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findeAnhandId(final UUID id) throws PersonenServiceException {
        try {
          return repo.findById(id).map(mapper::convert);
        } catch (final RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (Exception e) {
            throw new PersonenServiceException("Upps", e);
        }
    }
}
