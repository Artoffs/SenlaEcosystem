package com.ecosystem.models;

import com.ecosystem.repo.Environment;
import com.ecosystem.utils.Event;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Базовое представление сущности животного
 */

public class Animal {
    private UUID id;
    private AnimalSpecies species;
    private int age;
    private int health = 100;
    private double waterConsumption;
    private boolean isAlive;
    private boolean canReproduce;
    private int positionX;
    private int positionY;

    public Animal() {
    }

    public Animal(AnimalSpecies species, int health, int positionX, int positionY) {
        this.id = UUID.randomUUID();
        this.species = species;
        this.age = 0;
        this.health = health;
        this.waterConsumption = setWaterConsumption(species);
        this.isAlive = true;
        this.canReproduce = age >= 2;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public UUID getId() {
        return id;
    }

    public AnimalSpecies getSpecies() {
        return species;
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

    public int getHealth() {
        return health;
    }

    public Plant eat(List<Plant> plants) {
        Plant eatenPlants = null;
        for (Plant plant : plants) {
            if (this.positionX == plant.getPositionX() && this.positionY == plant.getPositionY() && plant.isAlive()) {
                this.health += plant.getHealth();
                plant.die();
                eatenPlants = plant;
                break;
            }
        }
        return eatenPlants;
    }

    public Animal predEat(List<Animal> herbAnimals) {
        Animal eatenAnimal = null;
        for (Animal animal : herbAnimals) {
            if (this.positionX == animal.getPositionX() && this.positionY == animal.getPositionY() && animal.isAlive()) {
                this.health += animal.getHealth();
                animal.die();
                eatenAnimal = animal;
                break;
            }
        }
        return eatenAnimal;
    }

    public Event drink(Environment environment) {
        if (environment.getResource(ResourceType.WATER) < waterConsumption) {
            this.die();
            return new Event(String.format("%s умер от жажды!", this));
        }
        environment.updateResource(ResourceType.WATER, -waterConsumption);
        return new Event(String.format("%s попил!", this));
    }

    public Event move(Environment environment) {
        Random random = new Random();
        int moveX = random.nextInt(3) - 1;
        int moveY = random.nextInt(3) - 1;
        this.positionX += moveX;
        this.positionY += moveY;

        // Проверка границ среды обитания
        if (this.positionX < 0) this.positionX = 0;
        if (this.positionY < 0) this.positionY = 0;
        if (this.positionX > environment.getWidth()) this.positionX = environment.getWidth();
        if (this.positionY > environment.getHeight()) this.positionY = environment.getHeight();

        return new Event(String.format("%s подвигался на %d по x и на %d по у", this, moveX, moveY));
    }

    public Event reproduce(List<Animal> animals, Random random, Environment environment) {
        if (canReproduce && random.nextInt(100) <= 10
                && environment.getResource(ResourceType.WATER) > 0
                && environment.getCondition(ConditionType.TEMPERATURE) > 15) { // 1% шанс на размножение
            Animal newAnimal = new Animal(species, 50, this.positionX, this.positionY);
            animals.add(newAnimal);
            return new Event(String.format("Появился новый детеныш %s", this.getSpecies()));
        }
        return new Event("");
    }

    public Event grow() {
        age++;

        if (age > 2 && age < 20) {
            canReproduce = true;
            return new Event(this + " теперь может родить");
        }
        if (age > 20) {
            isAlive = false;
            return new Event(this + " умер от старости");
        }
        return new Event(this + " старше на 1 день");
    }

    public Event die() {
        isAlive = false;
        return new Event(this + " умер");
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
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
