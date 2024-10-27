package com.ecosystem;

import com.ecosystem.models.*;
import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;

import java.util.Scanner;

public class Ecosystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Simulation sim = SimulationManager.startNew();
        sim.addAnimal(AnimalSpecies.DEER, 100, 0, 0);
        sim.addAnimal(AnimalSpecies.WOLF, 100, 1, 0);
        sim.addPlant(PlantSpecies.SUNFLOWER, 1, 1);
        System.out.println(sim.getSimulationService().getAllAnimals());
        // SimulationManager.saveCurrentSimulation(sim);
    }
}
