package com.ecosystem.simulation;

import com.ecosystem.services.SimulationService;

import java.util.concurrent.TimeUnit;

public class EnvironmentSimulation implements Runnable {
    private final SimulationService simulationService;

    public EnvironmentSimulation(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            simulationService.updateConditions();
            System.out.println(simulationService.getResources());
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}