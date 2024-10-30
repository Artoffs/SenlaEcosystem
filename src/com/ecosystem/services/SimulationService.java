package com.ecosystem.services;

import com.ecosystem.models.*;
import com.ecosystem.repo.AnimalRepository;
import com.ecosystem.repo.Environment;
import com.ecosystem.repo.PlantRepository;
import com.ecosystem.utils.Event;
import com.ecosystem.utils.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Класс-сервис для симуляции. Содержит в себе классы репозитории, позволяет
 * управлять экосистемой.
 */

public class SimulationService {
    private final AnimalRepository animalRepository;
    private final PlantRepository plantRepository;
    private final Environment environment;
    private final Random random;

    public SimulationService() {
        this.animalRepository = new AnimalRepository();
        this.plantRepository = new PlantRepository();
        this.environment = new Environment();
        this.random = new Random();
    }

    public SimulationService(List<Animal> animals, List<Plant> plants, Map<ConditionType, Double> conditions, Map<ResourceType, Double> resources) {
        this.animalRepository = new AnimalRepository(animals);
        this.plantRepository = new PlantRepository(plants);
        this.environment = new Environment(conditions, resources);
        this.random = new Random();
    }

    public void addAnimal(Animal animal) {
        animalRepository.createAnimal(animal);
    }

    public boolean deleteAnimal(UUID uuid) {
        Animal animal = getAnimal(uuid);
        if (animal != null) {
            animalRepository.deleteAnimal(animal);
            return true;
        }
        return false;
    }

    public void addPlant(Plant plant) {
        plantRepository.createPlant(plant);
    }

    public boolean deletePlant(UUID uuid) {
        Plant plant = getPlant(uuid);
        if (plant != null) {
            plantRepository.deletePlant(plant);
            return true;
        }
        return false;
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

    public void updateConditions() throws InterruptedException {
        environment.updateConditions();
    }

    public void updateCondition(ConditionType type, Double value) {
        environment.setCondition(type, value);
    }

    public void updateResource(ResourceType type, Double value) {
        environment.setResource(type, value);
    }

    public int getWidthEnv() {
        return environment.getWidth();
    }

    public int getHeightEnv() {
        return environment.getHeight();
    }

    public Double getWaterConsumption() {
        Double animalCons = animalRepository.getAnimals().stream().mapToDouble(Animal::getWaterConsumption).sum();
        Double plantCons = plantRepository.getPlants().stream().mapToDouble(Plant::getWaterConsumption).sum();
        return animalCons + plantCons;
    }

    public void runHerbivoreAnimalLogic() throws InterruptedException {
        List<Animal> newAnimals = new ArrayList<>();
        List<Plant> eatenPlants = new ArrayList<>();

        for (Animal animal : animalRepository.getHerbivoreAnimals()) {
            if (animal.isAlive()) {
                Logger.log(animal.grow());
                TimeUnit.SECONDS.sleep(1);
                Logger.log(animal.move(environment));
                TimeUnit.SECONDS.sleep(1);
                Plant eatenPlant = animal.eat(plantRepository.getPlants());
                TimeUnit.SECONDS.sleep(1);
                if (eatenPlant != null) {
                    eatenPlants.add(eatenPlant);
                    Logger.log(new Event(animal + " съел " + eatenPlant));
                }
                Logger.log(animal.drink(environment));
                if (!animal.isAlive()) continue;
                TimeUnit.SECONDS.sleep(1);
                Logger.log(animal.reproduce(newAnimals, random, environment));
                TimeUnit.SECONDS.sleep(1);
                if (animal.getHealth() <= 0) {
                    Logger.log(animal.die());
                }
                TimeUnit.SECONDS.sleep(1);
            } else {
                animalRepository.deleteAnimal(animal);
            }
        }

        animalRepository.updateAnimals(newAnimals); // Добавляем новых животных в популяцию
        plantRepository.deleteEatenPlants(eatenPlants); // Удаляем съеденные растения из репозитория

    }

    public void runPredatorLogic() throws InterruptedException {
        List<Animal> newAnimals = new ArrayList<>();
        List<Animal> eatenAnimals = new ArrayList<>();

        for (Animal animal : animalRepository.getPredatorAnimals()) {
            if (animal.isAlive()) {
                Logger.log(animal.grow());
                TimeUnit.SECONDS.sleep(1);
                Logger.log(animal.move(environment));
                TimeUnit.SECONDS.sleep(1);
                Animal eatenAnimal = animal.predEat(animalRepository.getHerbivoreAnimals());
                TimeUnit.SECONDS.sleep(1);
                if (eatenAnimal != null) {
                    eatenAnimals.add(eatenAnimal);
                    Logger.log(new Event(animal + " съел " + eatenAnimal));
                }
                TimeUnit.SECONDS.sleep(1);
                Logger.log(animal.drink(environment));
                TimeUnit.SECONDS.sleep(1);
                Logger.log(animal.reproduce(newAnimals, random, environment));
                if (animal.getHealth() <= 0) {
                    Logger.log(animal.die());
                }
                TimeUnit.SECONDS.sleep(1);
            } else {
                animalRepository.deleteAnimal(animal);
            }
        }

        animalRepository.updateAnimals(newAnimals);
        animalRepository.deleteDiedAnimals(eatenAnimals);
    }

    public void runPlantLogic() throws InterruptedException {
        List<Plant> newPlants = new ArrayList<>();

        for (Plant plant : plantRepository.getPlants()) {
            if (plant.isAlive()) {
                Logger.log(plant.grow());
                TimeUnit.SECONDS.sleep(1);
                Logger.log(plant.drink(environment));
                TimeUnit.SECONDS.sleep(1);
                Logger.log(plant.reproduce(newPlants, random));
                TimeUnit.SECONDS.sleep(1);
            } else {
                plantRepository.deletePlant(plant);
            }
        }
        plantRepository.updatePlants(newPlants);
    }

}
