package de.fi.webapp.service.internal;


import de.fi.webapp.persistence.SchweinRepository;
import de.fi.webapp.service.SchweinService;
import de.fi.webapp.service.SchweineServiceException;
import de.fi.webapp.service.mapper.SchweinMapper;
import de.fi.webapp.service.model.Schwein;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service

@Transactional(rollbackFor = SchweineServiceException.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class SchweinServiceImpl implements SchweinService {

    private final SchweinRepository schweinRepository;

    private final SchweinMapper schweinMapper;

    public SchweinServiceImpl(final SchweinRepository schweinRepository, final SchweinMapper schweinMapper) {
        this.schweinRepository = schweinRepository;
        this.schweinMapper = schweinMapper;
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
    public void speichern(final Schwein schwein) throws SchweineServiceException {
        try {
            checkSchwein(schwein);
            schweinRepository.save(schweinMapper.convert(schwein));

        } catch (RuntimeException e) {
            throw new SchweineServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }



    @Override
    public boolean aendern(final Schwein person) throws SchweineServiceException {
        try {
            if(! schweinRepository.existsById(person.getId())) {
                return false;
            }
            checkSchwein(person);
            schweinRepository.save(schweinMapper.convert(person));

            return true;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }

    @Override
    public boolean loeschen(final UUID id) throws SchweineServiceException {
        try {
            if(! schweinRepository.existsById(id)) {
                return false;
            }

            schweinRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }

    @Override
    public Optional<Schwein> findeAnhandId(final UUID id) throws SchweineServiceException {
        try {
            return schweinRepository.findById(id).map(schweinMapper::convert);
        }catch (Exception e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {
            return schweinMapper.convert(schweinRepository.findAll());
        } catch (Exception e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public boolean fuettern(final UUID id) throws SchweineServiceException {
        Optional<Schwein> schweinOptional = findeAnhandId(id);
        if(schweinOptional.isEmpty()) return false;

        Schwein schwein = schweinOptional.get();
        schwein.fuettern();
        aendern(schwein);
        return true;
    }

    private void checkSchwein(final Schwein schwein) throws SchweineServiceException {
        if(schwein == null)
            throw new SchweineServiceException("Parameter darf nicht null sein");
        if(schwein.getName() == null || schwein.getName().length() < 2)
            throw new SchweineServiceException("Vorname zu kurz");
        if(schwein.getGewicht() < 10)
            throw new SchweineServiceException("zu leicht");

    }
}
