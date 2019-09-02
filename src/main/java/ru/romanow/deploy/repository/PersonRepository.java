package ru.romanow.deploy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanow.deploy.domain.Person;

import java.util.Optional;

public interface PersonRepository
        extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
}
