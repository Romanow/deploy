package ru.romanow.deploy.service;

import ru.romanow.deploy.model.CreatePersonRequest;
import ru.romanow.deploy.model.PersonInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URI;
import java.util.List;

public interface PersonService {

    @Nonnull
    List<PersonInfo> getAllPersons();

    @Nonnull
    List<PersonInfo> findByName(@Nonnull String name);

    URI createPerson(@Nonnull CreatePersonRequest request);
}
