package com.ecosystem.simulation;

import com.ecosystem.services.SimulationService;

public class PredatorSimulation extends Thread {

    private final SimulationService simulationService;

    public PredatorSimulation(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Override
    public void run() {
        simulationService.runPredatorLogic();
    }
}
