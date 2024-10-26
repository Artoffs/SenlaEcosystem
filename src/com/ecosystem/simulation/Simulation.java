package com.ecosystem.simulation;

import com.ecosystem.models.*;
import com.ecosystem.services.PredictionService;
import com.ecosystem.services.SimulationService;

import java.util.List;

public class Simulation {
    private final SimulationService simulationService;
    private final PredictionService predictionService;

    public Simulation(SimulationService simulationService) {
        this.simulationService = simulationService;
        this.predictionService = new PredictionService(this);
    }

    public Simulation() {
        this.simulationService = new SimulationService();
        this.predictionService = new PredictionService(this);
    }

    public void addAnimal(AnimalSpecies species) {
        simulationService.addAnimal(new Animal(species));
    }

    public void addPlant(PlantSpecies species) {
        simulationService.addPlant(new Plant(species));
    }

    public void addCondition(ConditionType type, Double value) {
        simulationService.addCondition(new Condition(type, value));
    }

    public void addResource(ResourceType type, Double value) {
        simulationService.addResource(new Resource(type, value));
    }

    public List<Animal> getAllAnimals() {
        return simulationService.getAllAnimals();
    }

    public List<Plant> getAllPlants() {
        return simulationService.getAllPlants();
    }

    public Resource getResource(ResourceType type) {
        return simulationService.getResource(type);
    }

    public Condition getCondition(ConditionType type) {
        return simulationService.getCondition(type);
    }

    public double getFullWaterConsumption() {
        List<Animal> allAnimals = simulationService.getAllAnimals();
        List<Plant> allPlants = simulationService.getAllPlants();
        double animalCons = allAnimals.stream()
                .mapToDouble(Animal::getWaterConsumption)
                .sum();
        double plantCons = allPlants.stream()
                .mapToDouble(Plant::getWaterConsumption)
                .sum();
        return animalCons + plantCons;
    }

    public double getQualityIndex() {
        return predictionService.qualityIndex(simulationService.getAllConditions(), simulationService.getAllResources());
    }

    public SimulationService getEcosystemService() {
        return simulationService;
    }


}
