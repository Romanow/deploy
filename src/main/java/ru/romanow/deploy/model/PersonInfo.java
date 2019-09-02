package ru.romanow.deploy.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PersonInfo {
    private final String name;
    private final Integer age;
}
