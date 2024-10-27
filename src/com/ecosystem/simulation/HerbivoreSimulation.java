package com.ecosystem.simulation;

import com.ecosystem.services.SimulationService;

public class HerbivoreSimulation extends Thread {

    private final SimulationService simulationService;

    public HerbivoreSimulation(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Override
    public void run() {
        simulationService.runHerbivoreAnimalLogic();
    }
}
