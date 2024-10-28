package com.ecosystem.simulation;

import com.ecosystem.services.SimulationService;

import java.util.concurrent.TimeUnit;

public class PlantSimulation extends Thread {
    private final SimulationService simulationService;

    public PlantSimulation(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Override
    public void run() {
        while(!simulationService.getAllPlants().isEmpty()) {
            try {
                simulationService.runPlantLogic();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.interrupted();
    }
}
