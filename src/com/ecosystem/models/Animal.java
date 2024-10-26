package com.ecosystem.models;

import java.util.UUID;

public class Animal {
    private UUID id;
    private AnimalSpecies species;
    private int age;
    private double waterConsumption;
    private boolean isAlive;

    public Animal() {
    }

    public Animal(AnimalSpecies species)
    {
        this.id = UUID.randomUUID();
        this.species = species;
        this.age = 0;
        this.waterConsumption = setWaterConsumption(species);
        this.isAlive = true;
    }

    public UUID getId() {
        return id;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public boolean isAlive() {
        return isAlive;
    }

    private double setWaterConsumption(AnimalSpecies type) {
        return switch (type) {
            case DEER -> 2d;
            case WOLF -> 1d;
        };
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public void die()
    {
        isAlive = false;
    }

    public void grow()
    {
        age++;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", isAlive=" + isAlive +
                '}';
    }
}
