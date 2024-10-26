package com.ecosystem.simulation;

import com.ecosystem.models.Animal;
import com.ecosystem.models.Condition;
import com.ecosystem.models.Plant;
import com.ecosystem.models.Resource;
import com.ecosystem.services.SimulationService;
import com.ecosystem.utils.Serializer;

import java.time.LocalDateTime;
import java.util.List;

public class SimulationManager {

    private static final String PATH = "simulations/";

    public static Simulation startNew() {
        return new Simulation();
    }

    public static Simulation continueExist(String folderName)
    {
        List<Animal> animals = Serializer.readFromFile(PATH + folderName + "/" + "animals.txt", Animal.class);
        List<Plant> plants = Serializer.readFromFile(PATH + folderName  + "/" + "plants.txt", Plant.class);
        List<Condition> conditions = Serializer.readFromFile(PATH + folderName  + "/" + "conditions.txt", Condition.class);
        List<Resource> resources = Serializer.readFromFile(PATH + folderName  + "/" + "resources.txt", Resource.class);
        SimulationService serv = new SimulationService(animals, plants, conditions, resources);
        return new Simulation(serv);
    }

    public static void saveCurrentSimulation(Simulation simulation) {
        String folder = LocalDateTime.now() + "/";
        SimulationService simulationService = simulation.getEcosystemService();
        Serializer.serialize(simulationService.getAllAnimals(), PATH+folder+"animals.txt");
        Serializer.serialize(simulationService.getAllPlants(), PATH+folder+"plants.txt");
        Serializer.serialize(simulationService.getAllConditions(), PATH+folder+"conditions.txt");
        Serializer.serialize(simulationService.getAllResources(), PATH+folder+"resources.txt");
    }
}
