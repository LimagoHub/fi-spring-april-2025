package de.fi.webapp.demo;

import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.entity.PersonenRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final PersonenRepository personenRepository;


    @PostConstruct
    public void test() {
       /* PersonEntity person = PersonEntity.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();
        personenRepository.save(person);

        person = PersonEntity.builder().id(UUID.randomUUID()).vorname("John").nachname("Wayne").build();
        personenRepository.save(person);

        person = PersonEntity.builder().id(UUID.randomUUID()).vorname("John").nachname("Rambo").build();
        personenRepository.save(person);

        person = PersonEntity.builder().id(UUID.randomUUID()).vorname("John").nachname("McClaine").build();
        personenRepository.save(person);

        person = PersonEntity.builder().id(UUID.randomUUID()).vorname("John").nachname("Wick").build();
        personenRepository.save(person);

        person = PersonEntity.builder().id(UUID.randomUUID()).vorname("John boy ").nachname("Walton").build();
        personenRepository.save(person);

        */

        var result = personenRepository.findTinies();
        result.forEach(System.out::println);

    }
}
