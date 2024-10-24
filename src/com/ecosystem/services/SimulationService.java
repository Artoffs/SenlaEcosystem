package com.ecosystem.services;

import com.ecosystem.models.*;
import com.ecosystem.repo.AnimalRepository;
import com.ecosystem.repo.ConditionsRepository;
import com.ecosystem.repo.PlantRepository;

import java.util.List;
import java.util.Map;

public class EcosystemService {
    AnimalRepository animalRepository;
    PlantRepository plantRepository;
    ConditionsRepository conditionsRepository;

    public EcosystemService() {
        this.animalRepository = new AnimalRepository();
        this.plantRepository = new PlantRepository();
        this.conditionsRepository = new ConditionsRepository();
    }

    public void addAnimal(AnimalSpecies species) {
        animalRepository.createAnimal(new Animal(species));
    }

    public void addPlant(PlantSpecies species) {
        plantRepository.createPlant(new Plant(species));
    }

    public void addCondition(ConditionType type, Double value) {
        conditionsRepository.createCondition(type, value);
    }

    // TODO
    public void simulateInteractions() {
        List<Animal> animals = animalRepository.getAnimals();
        List<Plant> plants = plantRepository.getPlants();
        Map<ConditionType, Double> conditions = conditionsRepository.getConditions();
    }
}
