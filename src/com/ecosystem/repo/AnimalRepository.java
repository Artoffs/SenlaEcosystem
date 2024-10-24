package com.ecosystem.repo;

import com.ecosystem.models.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
    private final List<Animal> animals;

    public AnimalRepository() {
        this.animals = new ArrayList<>();
    }

    // C
    public void createAnimal(Animal animal)
    {
        animals.add(animal);
    }

    // R
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

    // D
    public void deleteAnimal(Animal animal) {
        animals.remove(animal);
    }
}
