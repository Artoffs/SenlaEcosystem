package com.ecosystem.models;

import com.ecosystem.repo.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
    private static final Random random = new Random();

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

    public boolean drink(Environment environment) {
        if (environment.getResource(ResourceType.WATER) < 0) {
            this.die();
            return false;
        }
        return true;
    }

    public void move(Environment environment) {
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
    }

    public void reproduce(List<Animal> animals) {
        if (canReproduce && random.nextInt(100) <= 100) { // 1% шанс на размножение
            animals.add(new Animal(species, 50, this.positionX, this.positionY));
        }
    }

    public void grow() {
        age++;
        if (age > 20) {
            isAlive = false;
        }
    }

    public void die() {
        isAlive = false;
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
