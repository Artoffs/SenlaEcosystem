package com.ecosystem.simulation;

import com.ecosystem.models.Animal;
import com.ecosystem.models.Plant;
import com.ecosystem.services.EcosystemService;
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
        EcosystemService serv = new EcosystemService(animals, plants);
        return new Simulation(serv);
    }

    public static void saveCurrentSimulation(Simulation simulation) {
        String folder = LocalDateTime.now().toString() + "/";
        EcosystemService ecosystemService = simulation.getEcosystemService();
        Serializer.serialize(ecosystemService.getAllAnimals(), PATH+folder+"animals.txt");
        Serializer.serialize(ecosystemService.getAllPlants(), PATH+folder+"plants.txt");
    }
}
