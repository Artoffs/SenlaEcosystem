package com.ecosystem.models;

import java.util.UUID;

public class Condition {
    private UUID id;
    private ConditionType type;
    private double value;

    public Condition() {

    }

    public Condition(ConditionType type, double value) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public ConditionType getType() {
        return type;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
