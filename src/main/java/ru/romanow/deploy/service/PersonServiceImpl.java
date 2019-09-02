package ru.romanow.deploy.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanow.deploy.domain.Person;
import ru.romanow.deploy.model.CreatePersonRequest;
import ru.romanow.deploy.model.PersonInfo;
import ru.romanow.deploy.repository.PersonRepository;

import javax.annotation.Nonnull;
import java.net.URI;
import java.util.List;

import static java.util.List.of;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl
        implements PersonService {
    private final PersonRepository personRepository;

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<PersonInfo> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(this::buildPersonInfo)
                .collect(toList());
    }

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<PersonInfo> findByName(@Nonnull String name) {
        return personRepository.findByName(name)
                .map(p -> of(buildPersonInfo(p)))
                .orElse(Lists.newArrayList());
    }

    @Override
    public URI createPerson(@Nonnull CreatePersonRequest request) {
        Person person = new Person()
                .setName(request.getName())
                .setAge(request.getAge());
        person = personRepository.save(person);
        return URI.create("/api/v1/" + person.getId());
    }

    @Nonnull
    private PersonInfo buildPersonInfo(@Nonnull Person person) {
        return new PersonInfo(person.getName(), person.getAge());
    }
}
