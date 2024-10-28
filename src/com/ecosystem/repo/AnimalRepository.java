package com.ecosystem.repo;

import com.ecosystem.models.Animal;
import com.ecosystem.models.AnimalSpecies;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimalRepository {
    private final List<Animal> animals;

    public AnimalRepository() {
        this.animals = new CopyOnWriteArrayList<>();
    }

    public AnimalRepository(List<Animal> animals) {
        this.animals = animals;
    }

    // C
    public void createAnimal(Animal animal)
    {
        animals.add(animal);
    }

    // R
    public Animal getAnimal(UUID uuid) {
        Optional<Animal> optionalAnimal = animals.stream()
                .filter(animal -> animal.getId().equals(uuid))
                .findFirst();
        return optionalAnimal.orElseThrow();
    }

    public List<Animal> getHerbivoreAnimals() {
        return animals.stream()
                .filter(animal -> animal.getSpecies().equals(AnimalSpecies.DEER))
                .toList();
    }

    public List<Animal> getPredatorAnimals() {
        return animals.stream()
                .filter(animal -> animal.getSpecies().equals(AnimalSpecies.WOLF))
                .toList();
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    // U
    public void updateAnimal(Animal animal) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId().equals(animal.getId())) {
                animals.set(i, animal);
                break;
            }
        }
    }

    public void updateAnimals(List<Animal> newAnimals) {
        this.animals.addAll(newAnimals);
    }

    // D
    public void deleteAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void deleteDiedAnimals(List<Animal> diedAnimals) {
        if(!diedAnimals.isEmpty()) diedAnimals.forEach(this::deleteAnimal);
    }
}
