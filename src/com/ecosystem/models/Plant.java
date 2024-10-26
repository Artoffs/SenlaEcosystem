package com.ecosystem.models;

import java.util.UUID;

public class Plant {
    private UUID id;
    private PlantSpecies species;
    private double waterConsumption;
    private int age;
    private boolean isAlive;

    public Plant() {
    }

    public Plant(PlantSpecies species)
    {
        this.id = UUID.randomUUID();
        this.species = species;
        this.waterConsumption = setWaterConsumption(species);
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

    private double setWaterConsumption(PlantSpecies type) {
        return switch (type) {
            case SUNFLOWER -> 1d;
            case TREE -> 3d;
        };
    }

    public double getWaterConsumption() {
        return waterConsumption;
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
