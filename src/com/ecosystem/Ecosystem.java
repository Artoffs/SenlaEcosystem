package com.ecosystem;

import com.ecosystem.models.AnimalSpecies;
import com.ecosystem.models.PlantSpecies;
import com.ecosystem.services.EcosystemService;
import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;

import java.util.Scanner;

public class Ecosystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulation sim = SimulationManager.continueExist("2024-10-25T14_26_19.169064");
        System.out.println(sim.getEcosystemService().getAllAnimals());
    }
}
