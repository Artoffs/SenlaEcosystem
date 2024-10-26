package com.ecosystem.services;

import com.ecosystem.models.*;
import com.ecosystem.repo.AnimalRepository;
import com.ecosystem.repo.ConditionsRepository;
import com.ecosystem.repo.PlantRepository;
import com.ecosystem.repo.ResourceRepository;

import java.util.List;
import java.util.UUID;

public class SimulationService {
    private final AnimalRepository animalRepository;
    private final PlantRepository plantRepository;
    private final ConditionsRepository conditionsRepository;
    private final ResourceRepository resourceRepository;

    public SimulationService() {
        this.animalRepository = new AnimalRepository();
        this.plantRepository = new PlantRepository();
        this.conditionsRepository = new ConditionsRepository();
        this.resourceRepository = new ResourceRepository();
    }

    public SimulationService(List<Animal> animals, List<Plant> plants, List<Condition> conditions, List<Resource> resources)
    {
        this.animalRepository = new AnimalRepository(animals);
        this.plantRepository = new PlantRepository(plants);
        this.conditionsRepository = new ConditionsRepository(conditions);
        this.resourceRepository = new ResourceRepository(resources);
    }

    public void addAnimal(Animal animal) {
        animalRepository.createAnimal(animal);
    }

    public void addPlant(Plant plant) {
        plantRepository.createPlant(plant);
    }

    public void addCondition(Condition condition) {
        conditionsRepository.createCondition(condition);
    }

    public void addResource(Resource resource) {
        resourceRepository.createResource(resource);
    }

    public Animal getAnimal(UUID uuid) {
        return animalRepository.getAnimal(uuid);
    }
    public Plant getPlant(UUID uuid) {
        return plantRepository.getPlant(uuid);
    }
    public Condition getCondition(ConditionType type) {
        return conditionsRepository.getCondition(type);
    }
    public Resource getResource(ResourceType type) {
        return resourceRepository.getResource(type);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.getAnimals();
    }

    public List<Plant> getAllPlants() {
        return plantRepository.getPlants();
    }

    public List<Condition> getAllConditions() {
        return conditionsRepository.getConditions();
    }

    public List<Resource> getAllResources() {
        return resourceRepository.getResources();
    }

    // TODO
    public void simulateInteractions() {
        List<Animal> animals = animalRepository.getAnimals();
        List<Plant> plants = plantRepository.getPlants();
    }
}
