package com.ecosystem.simulation;

import com.ecosystem.models.Animal;
import com.ecosystem.models.Condition;
import com.ecosystem.models.Plant;
import com.ecosystem.models.Resource;
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
        List<Condition> conditions = Serializer.readFromFile(PATH + folderName  + "/" + "conditions.txt", Condition.class);
        List<Resource> resources = Serializer.readFromFile(PATH + folderName  + "/" + "resources.txt", Resource.class);
        EcosystemService serv = new EcosystemService(animals, plants, conditions, resources);
        return new Simulation(serv);
    }

    public static void saveCurrentSimulation(Simulation simulation) {
        String folder = LocalDateTime.now() + "/";
        EcosystemService ecosystemService = simulation.getEcosystemService();
        Serializer.serialize(ecosystemService.getAllAnimals(), PATH+folder+"animals.txt");
        Serializer.serialize(ecosystemService.getAllPlants(), PATH+folder+"plants.txt");
        Serializer.serialize(ecosystemService.getAllConditions(), PATH+folder+"conditions.txt");
        Serializer.serialize(ecosystemService.getAllResources(), PATH+folder+"resources.txt");
    }
}
