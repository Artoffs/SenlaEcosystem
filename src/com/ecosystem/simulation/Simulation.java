package com.ecosystem.simulation;

import com.ecosystem.models.*;
import com.ecosystem.services.PredictionService;
import com.ecosystem.services.SimulationService;

import java.util.UUID;

/**
 * Класс, представляющий симуляцию, работает с сервисами симуляции и предсказания. Так же запускает
 * потоки развития. Так же имеет интерфейс для изменения экосистемы.
 */

public class Simulation {
    private final SimulationService simulationService;
    private final PredictionService predictionService;

    private final HerbivoreSimulation herbivoreSimulation;
    private final PredatorSimulation predatorSimulation;
    private final EnvironmentSimulation environmentSimulation;
    private final PlantSimulation plantSimulation;

    public Simulation(SimulationService simulationService) {
        this.simulationService = simulationService;
        this.predictionService = new PredictionService(this);
        this.herbivoreSimulation = new HerbivoreSimulation(simulationService);
        this.predatorSimulation = new PredatorSimulation(simulationService);
        this.environmentSimulation = new EnvironmentSimulation(simulationService);
        this.plantSimulation = new PlantSimulation(simulationService);
    }

    public Simulation() {
        this.simulationService = new SimulationService();
        this.predictionService = new PredictionService(this);
        this.herbivoreSimulation = new HerbivoreSimulation(simulationService);
        this.predatorSimulation = new PredatorSimulation(simulationService);
        this.environmentSimulation = new EnvironmentSimulation(simulationService);
        this.plantSimulation = new PlantSimulation(simulationService);
    }

    public void addAnimal(AnimalSpecies type, int health, int posX, int posY) {
        simulationService.addAnimal(new Animal(type, health, posX, posY));
    }

    public Animal getAnimal(UUID uuid) {
        return simulationService.getAnimal(uuid);
    }

    public boolean deleteAnimal(UUID uuid) {
        return simulationService.deleteAnimal(uuid);
    }

    public boolean deletePlant(UUID uuid) {
        return simulationService.deletePlant(uuid);
    }

    public void addPlant(PlantSpecies type, int posX, int posY) {
        simulationService.addPlant(new Plant(type, posX, posY));
    }

    public void getPrediction() {
        predictionService.getPrediction();
    }

    public Plant getPlant(UUID uuid) {
        return simulationService.getPlant(uuid);
    }

    public void setCondition(ConditionType type, Double value) {
        simulationService.updateCondition(type, value);
    }

    public void setResource(ResourceType type, Double value) {
        simulationService.updateResource(type, value);
    }

    public void run() throws InterruptedException {
        Thread herbThread = new Thread(herbivoreSimulation);
        herbThread.start();
        Thread predThread = new Thread(predatorSimulation);
        predThread.start();
        Thread plantThread = new Thread(plantSimulation);
        plantThread.start();
        Thread envThread = new Thread(environmentSimulation);
       envThread.start();

        herbThread.join();
        predThread.join();
        plantThread.join();
        envThread.join();
    }

    public SimulationService getSimulationService() {
        return simulationService;
    }

    public int getWidthEnv() {
        return simulationService.getWidthEnv();
    }

    public int getHeightEnv() {
        return simulationService.getHeightEnv();
    }
}

