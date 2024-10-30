package com.ecosystem.simulation;

import com.ecosystem.services.SimulationService;

import java.util.concurrent.TimeUnit;

/**
 * Класс, реализующий интерфейс Runnable. Поток развития хищных животных.
 */

public class PredatorSimulation implements Runnable {

    private final SimulationService simulationService;

    public PredatorSimulation(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @Override
    public void run() {
       while (!simulationService.getPredators().isEmpty()){
            try {
                simulationService.runPredatorLogic();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
