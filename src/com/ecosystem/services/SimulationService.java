package com.ecosystem.services;

import com.ecosystem.models.*;
import com.ecosystem.repo.AnimalRepository;
import com.ecosystem.repo.Environment;
import com.ecosystem.repo.PlantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SimulationService {
    private final AnimalRepository animalRepository;
    private final PlantRepository plantRepository;
    private final Environment environment;

    public SimulationService() {
        this.animalRepository = new AnimalRepository();
        this.plantRepository = new PlantRepository();
        this.environment = new Environment();
    }

    public SimulationService(List<Animal> animals, List<Plant> plants, Map<ConditionType, Double> conditions, Map<ResourceType, Double> resources) {
        this.animalRepository = new AnimalRepository(animals);
        this.plantRepository = new PlantRepository(plants);
        this.environment = new Environment(conditions, resources);
    }

    public void addAnimal(Animal animal) {
        animalRepository.createAnimal(animal);
    }

    public void addPlant(Plant plant) {
        plantRepository.createPlant(plant);
    }

    public Animal getAnimal(UUID uuid) {
        return animalRepository.getAnimal(uuid);
    }

    public Plant getPlant(UUID uuid) {
        return plantRepository.getPlant(uuid);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.getAnimals();
    }

    public List<Animal> getPredators() {
        return animalRepository.getAnimals().stream()
                .filter(animal -> animal.getSpecies().equals(AnimalSpecies.WOLF))
                .toList();
    }

    public List<Animal> getHerbivores() {
        return animalRepository.getAnimals().stream().
                filter(animal -> animal.getSpecies().equals(AnimalSpecies.DEER)).
                toList();
    }

    public Map<ResourceType, Double> getResources() {
        return environment.getResources();
    }

    public Map<ConditionType, Double> getConditions() {
        return environment.getConditions();
    }

    public List<Plant> getAllPlants() {
        return plantRepository.getPlants();
    }

    public void updateConditions() {
        environment.updateConditions();
    }

    public void runHerbivoreAnimalLogic() {
        List<Animal> newAnimals = new ArrayList<>();
        List<Plant> eatenPlants = new ArrayList<>();

        for (Animal animal : animalRepository.getHerbivoreAnimals()) {
            if (animal.isAlive()) {
                animal.grow();
                animal.move(environment);
                Plant eatenPlant = animal.eat(plantRepository.getPlants());
                if (eatenPlant != null) {
                    eatenPlants.add(eatenPlant);
                }
                animal.drink(environment);
                animal.reproduce(newAnimals); // Добавляем новую логику размножения
                if (animal.getHealth() <= 0) {
                    animal.die();
                }
            }
        }

        synchronized (animalRepository) {
            animalRepository.updateAnimals(newAnimals); // Добавляем новых животных в популяцию
        }
        synchronized (plantRepository) {
            plantRepository.deleteEatenPlants(eatenPlants); // Удаляем съеденные растения из репозитория
        }
    }

    public void runPredatorLogic() {
        List<Animal> newAnimals = new ArrayList<>();
        List<Animal> eatenAnimals = new ArrayList<>();

        for (Animal animal : animalRepository.getPredatorAnimals()) {
            if (animal.isAlive()) {
                animal.grow();
                animal.move(environment);
                Animal eatenAnimal = animal.predEat(animalRepository.getHerbivoreAnimals());
                if (eatenAnimal != null) {
                    eatenAnimals.add(eatenAnimal);
                }
                animal.drink(environment);
                animal.reproduce(newAnimals);
                if (animal.getHealth() <= 0) {
                    animal.die();
                }
            }
        }

        synchronized (animalRepository) {
            animalRepository.updateAnimals(newAnimals); // Добавляем новых животных в популяцию
            animalRepository.deleteDiedAnimals(eatenAnimals); // Удаляем съеденных животных из репозитория
        }
    }


}
