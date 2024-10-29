package com.ecosystem.services;

import com.ecosystem.models.ConditionType;
import com.ecosystem.models.ResourceType;
import com.ecosystem.simulation.Simulation;
import com.ecosystem.utils.Event;

import java.util.Map;


public class PredictionService {
    private final Simulation simulation;

    public PredictionService(Simulation simulation) {
        this.simulation = simulation;
    }

    public Event getPrediction() {
        String prediction = "";
        SimulationService simulationService = simulation.getSimulationService();
        Map<ConditionType, Double> conditions = simulationService.getConditions();
        Map<ResourceType, Double> resources = simulationService.getResources();
        if(simulationService.getAllPlants().size() + 3 < simulationService.getHerbivores().size()) {
            prediction += "ПРЕДУПРЕЖДЕНИЕ: В вашей экосистеме слишком мало растений для такого количества травоядных!\n";
        }
        if(simulationService.getHerbivores().size() < simulationService.getPredators().size()) {
            prediction += "ПРЕДУПРЕЖДЕНИЕ: В вашей экосистеме слишком мало травоядных для такого количества хищников!\n";
        }
        if (conditions.get(ConditionType.TEMPERATURE) > 35d) {
            prediction += "ПРЕДУПРЕЖДЕНИЕ: В вашей экосистеме слишком высокая температура!\n";
        }
        if (conditions.get(ConditionType.TEMPERATURE) < 10d) {
            prediction += "ПРЕДУПРЕЖДЕНИЕ: В вашей экосистеме слишком низкая температура!\n";
        }
        if (conditions.get(ConditionType.PRECIPITATION) < 5d) {
            prediction += "ПРЕДУПРЕЖДЕНИЕ: В вашей экосистеме слишком мало осадков!\n";
        }
        if (resources.get(ResourceType.WATER) / 3 < simulationService.getWaterConsumption()) {
            prediction += "ОПАСТНОСТЬ: В вашей экосистеме очень мало воды\n";
        }
        return new Event(prediction);
    }
}
