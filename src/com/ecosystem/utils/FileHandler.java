package com.ecosystem.utils;

import com.ecosystem.models.Animal;
import com.ecosystem.models.ConditionType;
import com.ecosystem.models.Plant;
import com.ecosystem.simulation.Simulation;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FileHandler {

    public static void writeToFile(Simulation simulation) {

        String folder = LocalDateTime.now().toString() + '/';

        List<Animal> allAnimals = simulation.getEcosystemService().getAllAnimals();
        List<Plant> allPlants = simulation.getEcosystemService().getAllPlants();
        Map<ConditionType, Double> allConditions = simulation.getEcosystemService().getAllConditions();

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(folder+"animals"))) {
            for(Animal animal : allAnimals) {
                objectOutputStream.writeObject("Я животное!\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(folder+"animals"))) {
            for(Plant animal : allPlants) {
                objectOutputStream.writeObject("Я растение!\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(folder+"animals"))) {
            for(Map.Entry<ConditionType, Double> condition : allConditions.entrySet()) {
                objectOutputStream.writeObject("Я условие!\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
