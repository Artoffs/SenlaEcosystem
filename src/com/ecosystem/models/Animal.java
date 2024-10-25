package com.ecosystem.models;

import java.util.UUID;

public class Animal {
    private UUID id;
    private AnimalSpecies species;
    private int age;
    private boolean isAlive;

    public Animal() {
    }

    public Animal(AnimalSpecies species)
    {
        this.id = UUID.randomUUID();
        this.species = species;
        this.age = 0;
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
