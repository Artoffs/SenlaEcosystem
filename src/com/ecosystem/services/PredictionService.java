package com.ecosystem.services;

import com.ecosystem.models.*;
import com.ecosystem.simulation.Simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictionService {
    private final Simulation simulation;

    public PredictionService(Simulation simulation) {
        this.simulation = simulation;
    }

    public Map<Object, Integer> getPrediction() {
        List<Animal> predators = simulation.getAllAnimals().stream()
                .filter(animal -> animal.getSpecies().equals(AnimalSpecies.WOLF))
                .toList();
        List<Animal> herbivores = simulation.getAllAnimals().stream().
                filter(animal -> animal.getSpecies().equals(AnimalSpecies.DEER)).
                toList();
        List<Plant> allPlants = simulation.getAllPlants();
        return new HashMap<>();
    }

    public double qualityIndex(List<Condition> conditions, List<Resource> resources) {
        double index = 50d;  // нормальные параметры системы: вода 8000 температура 20 влажность 50 осадки 10
        double availableWater = simulation.getResource(ResourceType.WATER).getValue();
        double humidity = simulation.getCondition(ConditionType.HUMIDITY).getValue();
        double precipitation = simulation.getCondition(ConditionType.PRECIPITATION).getValue();
        double temperature = simulation.getCondition(ConditionType.TEMPERATURE).getValue();;

        return index
                + ((availableWater - 8000) % 10)
                + ((temperature - 20) % 3)
                + ((humidity - 50) % 5)
                + ((precipitation - 10) % 100);

    }
}
