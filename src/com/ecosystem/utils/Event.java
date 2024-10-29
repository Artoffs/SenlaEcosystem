package com.ecosystem.utils;


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Event {
    private final String description;

    public Event(String description) {
        this.description = description;
    }

    public String getDescription() {
        return LocalTime.now().truncatedTo(ChronoUnit.SECONDS) + " " + description;
    }
}
