package com.zti.spring.data.jdbc.model;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import static lombok.AccessLevel.PACKAGE;

@With// Wither is important! We need it, because we are using immutable value objects...
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PACKAGE)
public
class Genre {
    public static final Genre UNDEFINED = new Genre(null, "Undefined");

    @Id
    private final Long id;
    private final String name;
}
