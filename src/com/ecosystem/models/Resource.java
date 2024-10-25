package com.ecosystem.models;

import java.util.UUID;

public class Resource {
    private UUID id;
    private ResourceType type;
    private Double value;

    public Resource() {
    }

    public Resource(ResourceType type, Double value){
        this.id = UUID.randomUUID();
        this.type = type;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public ResourceType getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
