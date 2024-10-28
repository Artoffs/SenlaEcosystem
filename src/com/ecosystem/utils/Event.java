package com.ecosystem.utils;

import java.util.UUID;

public class Event {
    private UUID id;
    private final String description;

    public Event(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
