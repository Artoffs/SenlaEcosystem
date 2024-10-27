package com.ecosystem.simulation;

import com.ecosystem.models.*;
import com.ecosystem.services.PredictionService;
import com.ecosystem.services.SimulationService;

import java.util.List;
import java.util.Random;

public class Simulation implements Runnable{
    private final SimulationService simulationService;
    private final PredictionService predictionService;

    public Simulation(SimulationService simulationService) {
        this.simulationService = simulationService;
        this.predictionService = new PredictionService(this);
    }

    public Simulation() {
        this.simulationService = new SimulationService();
        this.predictionService = new PredictionService(this);
    }

    public void addAnimal(AnimalSpecies species) {
        simulationService.addAnimal(new Animal(species));
    }

    public void addPlant(PlantSpecies species) {
        simulationService.addPlant(new Plant(species));
    }

    public void addCondition(ConditionType type, Double value) {
        simulationService.addCondition(new Condition(type, value));
    }

    public void addResource(ResourceType type, Double value) {
        simulationService.addResource(new Resource(type, value));
    }

    public List<Animal> getAllAnimals() {
        return simulationService.getAllAnimals();
    }

    public List<Plant> getAllPlants() {
        return simulationService.getAllPlants();
    }

    public Resource getResource(ResourceType type) {
        return simulationService.getResource(type);
    }

    public Condition getCondition(ConditionType type) {
        return simulationService.getCondition(type);
    }

    public double getFullWaterConsumption() {
        List<Animal> allAnimals = simulationService.getAllAnimals();
        List<Plant> allPlants = simulationService.getAllPlants();
        double animalCons = allAnimals.stream()
                .mapToDouble(Animal::getWaterConsumption)
                .sum();
        double plantCons = allPlants.stream()
                .mapToDouble(Plant::getWaterConsumption)
                .sum();
        return animalCons + plantCons;
    }

    public double getQualityIndex() {
        return predictionService.qualityIndex(simulationService.getAllConditions(), simulationService.getAllResources());
    }

    public SimulationService getEcosystemService() {
        return simulationService;
    }

    private void animalLogic() {
        List<Plant> allPlants = getAllPlants();
        Double availableWater = getResource(ResourceType.WATER).getValue();
        Random random = new Random();
        for (Animal a: simulationService.getAllAnimals()) {
            a.grow();
            if(a.getSpecies().equals(AnimalSpecies.DEER) && a.isAlive()) {
                switch (random.nextInt(0, 4)){
                    case 0:
                        continue;
                    case 1:
                        if(availableWater > 0) {
                            availableWater -= a.getWaterConsumption();
                        }
                        break;
                    case 2:
                        if(simulationService.getHerbivores().size() >= 2) {
                            simulationService.addAnimal(new Animal(AnimalSpecies.DEER));
                        }
                        break;
                    case 3:
                        if(a.getAge() > 10) {
                            a.die();
                        }
                    case 4:
                        Plant plant = allPlants.get(random.nextInt(allPlants.size()));
                        plant.die();
                }
            }
            if(a.getSpecies().equals(AnimalSpecies.WOLF)) {
                switch (random.nextInt(0, 4)){
                    case 0:
                        continue;
                    case 1:
                        if(availableWater > 0) {
                            availableWater -= a.getWaterConsumption();
                        }
                        System.out.println("Все работает!1");
                        break;
                    case 2:
                        if(simulationService.getPredators().size() >= 2) {
                            simulationService.addAnimal(new Animal(AnimalSpecies.WOLF));
                        }
                        System.out.println("Все работает!2");
                        break;
                    case 3:
                        if(a.getAge() > 10) {
                            a.die();
                        }
                        System.out.println("Все работает!3");
                        break;
                    case 4:
                        Plant plant = allPlants.get(random.nextInt(allPlants.size()));
                        plant.die();
                        System.out.println("Все работает!4");
                        break;
                }
            }
        simulationService.setResource(ResourceType.WATER, availableWater);
        }
    }

    private void plantLogic() {

    }


    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            animalLogic();
            plantLogic();
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
