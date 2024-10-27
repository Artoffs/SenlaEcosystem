package com.ecosystem.simulation;

import com.ecosystem.models.*;
import com.ecosystem.services.PredictionService;
import com.ecosystem.services.SimulationService;

import java.util.Random;

public class Simulation {
    private final SimulationService simulationService;
    private final PredictionService predictionService;
    private final Random random = new Random();

    private final HerbivoreSimulation herbivoreSimulation;
    private final PredatorSimulation predatorSimulation;

    public Simulation(SimulationService simulationService) {
        this.simulationService = simulationService;
        this.predictionService = new PredictionService(this);
        this.herbivoreSimulation = new HerbivoreSimulation(simulationService);
        this.predatorSimulation = new PredatorSimulation(simulationService);
    }

    public Simulation() {
        this.simulationService = new SimulationService();
        this.predictionService = new PredictionService(this);
        this.herbivoreSimulation = new HerbivoreSimulation(simulationService);
        this.predatorSimulation = new PredatorSimulation(simulationService);
    }

    public void addAnimal(AnimalSpecies type, int health, int posX, int posY) {
        simulationService.addAnimal(new Animal(type, health, posX, posY));
    }

    public void addPlant(PlantSpecies type, int posX, int posY) {
        simulationService.addPlant(new Plant(type, posX, posY));
    }

    public void setCondition(ConditionType type, Double value) {

    }

    public void run() {
        herbivoreSimulation.start();
        predatorSimulation.start();
    }

    public SimulationService getSimulationService() {
        return simulationService;
    }
}
