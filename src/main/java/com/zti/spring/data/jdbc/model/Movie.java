package com.zti.spring.data.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;

@Wither
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PACKAGE)
public class Movie { // AggregateRoot

    @Id
    private final Long id;
    private final LocalDateTime lastModified; // But not ZonedDateTime...
    private final UUID aggregateId;
    private final String content;
    private final Genre genre; // one-to-one

    public Movie withLastModifiedUpdated() {
        return this.withLastModified(LocalDateTime.now());
    }

    public static Movie of(String content) {
        return new Movie(null, LocalDateTime.now(), UUID.randomUUID(), content, null);
    }

    public static Movie of(String content, Genre genre) {
        return new Movie(null, LocalDateTime.now(), UUID.randomUUID(), content, genre);
    }
}

