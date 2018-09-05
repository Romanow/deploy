package ru.romanow.deploy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRequest {

    @NotNull(message = "field.not.null")
    private String name;

    @Min(value = 1, message = "field.min")
    @Max(value = 100, message = "field.max")
    private Integer age;
}
