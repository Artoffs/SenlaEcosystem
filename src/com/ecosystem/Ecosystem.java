package com.ecosystem;

import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;

import java.util.Scanner;

public class Ecosystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulation sim = SimulationManager.continueExist("2024-10-25T12_58_56.410839");
        System.out.println(sim);

    }
}
