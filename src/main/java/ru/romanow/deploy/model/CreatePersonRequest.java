package ru.romanow.deploy.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class CreatePersonRequest {

    @NotEmpty(message = "{field.not.empty")
    private String name;

    @NotEmpty(message = "{field.not.null")
    @Min(value = 0, message = "{field.min")
    @Max(value = 100, message = "{field.max")
    private Integer age;
}
