package com.ecosystem.simulation;

import com.ecosystem.services.SimulationService;

import java.util.concurrent.TimeUnit;

/**
 * Класс, реализующий интерфейс Runnable. Поток развития растений.
 */

public class PlantSimulation implements Runnable {
    private final SimulationService simulationService;

    public PlantSimulation(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Override
    public void run() {
        while (!simulationService.getAllPlants().isEmpty()) {
            try {
                simulationService.runPlantLogic();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
