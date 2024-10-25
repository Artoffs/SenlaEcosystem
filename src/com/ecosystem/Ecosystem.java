package com.ecosystem;

import com.ecosystem.models.AnimalSpecies;
import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Ecosystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulation sim = SimulationManager.continueExist("2024-10-25T16_37_16.264722500");
        System.out.println(sim.getEcosystemService().getAllAnimals());
        System.out.println(sim.getEcosystemService().getAllPlants());
        System.out.println(sim.getEcosystemService().getAllConditions());
    }
}
