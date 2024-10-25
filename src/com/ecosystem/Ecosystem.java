package com.ecosystem;

import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Ecosystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulation sim = SimulationManager.continueExist("2024-10-25T15_17_48.285272300");
        System.out.println(sim.getEcosystemService().getAllAnimals());
        System.out.println(sim.getEcosystemService().getAllPlants());
        System.out.println(sim.getEcosystemService().getAllConditions());
    }
}
