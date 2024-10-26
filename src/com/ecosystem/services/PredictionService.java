package com.ecosystem.services;

import com.ecosystem.models.AnimalSpecies;
import com.ecosystem.models.Condition;
import com.ecosystem.simulation.Simulation;

import java.util.List;
import java.util.Map;

public class PredictionService {
    private final Simulation simulation;

    public PredictionService(Simulation simulation) {
        this.simulation = simulation;
    }

    public Map<Object, Boolean> getPrediction() {

    }

    private boolean checkCondition(AnimalSpecies species, List<Condition> conditions) {
        Double humidity = simulation.
    }
}
