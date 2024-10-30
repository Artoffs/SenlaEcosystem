package com.ecosystem.utils;


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Класс, представляющий событие в экосистеме.
 * Каждое событие, происходящее в экосистеме,
 * оборачивается в объект данного класса
 */

public class Event {
    private final String description;

    public Event(String description) {
        this.description = description;
    }

    public String getDescription() {
        return LocalTime.now().truncatedTo(ChronoUnit.SECONDS) + " " + description;
    }
}
