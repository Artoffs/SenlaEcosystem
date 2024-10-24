package com.ecosystem.models;

import java.util.UUID;

public class Plant {
    private final UUID id;
    private final PlantSpecies species;
    private int age;
    private boolean isAlive;

    public Plant(PlantSpecies species)
    {
        this.id = UUID.randomUUID();
        this.species = species;
        this.age = 0;
        this.isAlive = true;
    }

    public UUID getId() {
        return id;
    }

    public PlantSpecies getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void grow()
    {
        age++;
    }

    public void die()
    {
        isAlive = false;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", isAlive=" + isAlive +
                '}';
    }
}
