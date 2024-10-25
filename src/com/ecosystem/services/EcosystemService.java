package com.ecosystem.services;

import com.ecosystem.models.*;
import com.ecosystem.repo.AnimalRepository;
import com.ecosystem.repo.ConditionsRepository;
import com.ecosystem.repo.PlantRepository;

import java.util.List;

public class EcosystemService {
    private final AnimalRepository animalRepository;
    private final PlantRepository plantRepository;
    private final ConditionsRepository conditionsRepository;

    public EcosystemService() {
        this.animalRepository = new AnimalRepository();
        this.plantRepository = new PlantRepository();
        this.conditionsRepository = new ConditionsRepository();
    }

    public EcosystemService(List<Animal> animals, List<Plant> plants, List<Condition> conditions)
    {
        this.animalRepository = new AnimalRepository(animals);
        this.plantRepository = new PlantRepository(plants);
        this.conditionsRepository = new ConditionsRepository(conditions);
    }

    public void addAnimal(AnimalSpecies species) {
        animalRepository.createAnimal(new Animal(species));
    }

    public void addPlant(PlantSpecies species) {
        plantRepository.createPlant(new Plant(species));
    }

    public void addCondition(ConditionType type, Double value) {
        conditionsRepository.createCondition(new Condition(type, value));
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

    // TODO
    public void simulateInteractions() {
        List<Animal> animals = animalRepository.getAnimals();
        List<Plant> plants = plantRepository.getPlants();
    }
}
