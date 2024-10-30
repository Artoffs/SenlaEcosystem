package com.ecosystem.simulation;

import com.ecosystem.models.Animal;
import com.ecosystem.models.ConditionType;
import com.ecosystem.models.Plant;
import com.ecosystem.models.ResourceType;
import com.ecosystem.services.SimulationService;
import com.ecosystem.utils.Serializer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Данный класс работает с симуляциями. Создает новую, продолжает существующую, а так же сохраняет текущую в папку
 * с текущей датой.
 */

public class SimulationManager {

    private static final String PATH = "simulations/";

    public static Simulation startNew() {
        return new Simulation();
    }

    public static Simulation continueExist(String folderName)
    {
        List<Animal> animals = Serializer.deserializeList(PATH + folderName + "/" + "animals.txt", Animal.class);
        List<Plant> plants = Serializer.deserializeList(PATH + folderName  + "/" + "plants.txt", Plant.class);
        Map<ResourceType, Double> resources = Serializer.deserializeMap(PATH + folderName  + "/" + "resources.txt", ResourceType.class);
        Map<ConditionType, Double> conditions = Serializer.deserializeMap(PATH + folderName  + "/" + "conditions.txt", ConditionType.class);

        SimulationService serv = new SimulationService(animals, plants, conditions, resources);
        return new Simulation(serv);
    }

    public static void saveCurrentSimulation(Simulation simulation) {
        String folder = LocalDateTime.now() + "/";
        SimulationService simulationService = simulation.getSimulationService();
        Serializer.serialize(simulationService.getAllAnimals(), PATH+folder+"animals.txt");
        Serializer.serialize(simulationService.getAllPlants(), PATH+folder+"plants.txt");
        Serializer.serialize(simulationService.getConditions(), PATH+folder+"conditions.txt");
        Serializer.serialize(simulationService.getResources(), PATH+folder+"resources.txt");
    }
}
