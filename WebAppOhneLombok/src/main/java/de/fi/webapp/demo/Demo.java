package de.fi.webapp.demo;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.persistence.entity.PersonEntity;

import de.fi.webapp.service.internal.MailServiceImpl;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

public class Demo {

    private final PersonenRepository personenRepository;
    private final MailServiceImpl service;

    public Demo(final PersonenRepository personenRepository, final MailServiceImpl service) {
        this.personenRepository = personenRepository;
        this.service = service;
    }

    @PostConstruct
    public void test() {
        /*
       PersonEntity person = new PersonEntity(UUID.randomUUID(),"John","Doe");
        personenRepository.save(person);

        person = new PersonEntity(UUID.randomUUID(),"John","Wayne");
        personenRepository.save(person);

        person = new PersonEntity(UUID.randomUUID(),"John","Rambo");
        personenRepository.save(person);

        person = new PersonEntity(UUID.randomUUID(),"John","McClain");
        personenRepository.save(person);

        person = new PersonEntity(UUID.randomUUID(),"John","Wick");
        personenRepository.save(person);

        person = new PersonEntity(UUID.randomUUID(),"John Boy","Walton");
        personenRepository.save(person);



        var result = personenRepository.findTinies();
        result.forEach(System.out::println);
*/
        service.send("Hallo");
    }
}
