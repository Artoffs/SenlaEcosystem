package com.ecosystem;

import com.ecosystem.models.*;
import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;

import java.util.Scanner;

public class Ecosystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Simulation sim = SimulationManager.startNew();
        sim.addPlant(PlantSpecies.SUNFLOWER);
        sim.addPlant(PlantSpecies.TREE);
        sim.addAnimal(AnimalSpecies.DEER);
        sim.addAnimal(AnimalSpecies.WOLF);
        System.out.println(sim.getAllAnimals());
        System.out.println(sim.getAllPlants());
        System.out.println(sim.getFullWaterConsumption());
        System.out.println(sim.getQualityIndex());
        new Thread(sim).start();
    }
}
