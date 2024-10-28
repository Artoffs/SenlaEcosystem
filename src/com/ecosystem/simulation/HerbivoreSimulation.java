package com.ecosystem.simulation;

import com.ecosystem.services.SimulationService;

import java.util.concurrent.TimeUnit;

public class HerbivoreSimulation extends Thread {

    private final SimulationService simulationService;

    public HerbivoreSimulation(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Override
    public void run() {
        while(!simulationService.getHerbivores().isEmpty()) {
            try {
                simulationService.runHerbivoreAnimalLogic();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.interrupted();
    }
}
