package com.ecosystem.models;

import com.ecosystem.repo.Environment;
import com.ecosystem.utils.Event;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Plant {
    private UUID id;
    private PlantSpecies species;
    private int age;
    private final int health = 10;
    private double waterConsumption;
    private boolean isAlive;
    private int positionX;
    private int positionY;
    private static final Random random = new Random();

    public Plant() {
    }

    public Plant(PlantSpecies species, int positionX, int positionY)
    {
        this.id = UUID.randomUUID();
        this.species = species;
        this.waterConsumption = setWaterConsumption(species);
        this.age = 0;
        this.isAlive = true;
        this.positionX = positionX;
        this.positionY = positionY;
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

    public Event grow()
    {
        age++;
        return new Event(this + " вырос");
    }

    public Event drink(Environment environment) {
        if (environment.getResource(ResourceType.WATER) <= 0) {
            this.die();
            return new Event(String.format("%s увял без воды!", this));
        }
        return new Event(String.format("%s потребил воду!", this));
    }

    public Event reproduce(List<Plant> plants) {
        if (random.nextInt(100) <= 10) { // 1% шанс на размножение
            Plant newPlant = new Plant(species, random.nextInt(this.positionX ) - 3, random.nextInt(this.positionY) - 3);
            plants.add(newPlant);
            return new Event(String.format("Появился новое растение %s", species));
        }
        return new Event("");
    }

    public Event die()
    {
        isAlive = false;
        return new Event(this + "увял");
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getHealth() {
        return health;
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
