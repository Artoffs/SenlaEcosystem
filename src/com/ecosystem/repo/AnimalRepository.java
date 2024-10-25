package com.ecosystem.repo;

import com.ecosystem.models.Animal;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Animal getAnimal(UUID uuid) {
        Optional<Animal> optionalAnimal = animals.stream()
                .filter(animal -> animal.getId().equals(uuid))
                .findFirst();
        return optionalAnimal.orElseThrow();
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

    // D
    public void deleteAnimal(Animal animal) {
        animals.remove(animal);
    }
}
