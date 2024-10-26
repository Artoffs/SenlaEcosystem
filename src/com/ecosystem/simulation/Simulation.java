package com.ecosystem.simulation;

import com.ecosystem.models.*;
import com.ecosystem.services.EcosystemService;

public class Simulation {
    private final EcosystemService ecosystemService;

    public Simulation(EcosystemService ecosystemService) {
        this.ecosystemService = ecosystemService;
    }

    public void addAnimal(AnimalSpecies species) {
        ecosystemService.addAnimal(new Animal(species));
    }

    public void addPlant(PlantSpecies species) {
        ecosystemService.addPlant(new Plant(species));
    }

    public void addCondition(ConditionType type, Double value) {
        ecosystemService.addCondition(new Condition(type, value));
    }

    public void addResource(ResourceType type, Double value) {
        ecosystemService.addResource(new Resource(type, value));
    }

    public Simulation() {
        this.ecosystemService = new EcosystemService();
    }

    public EcosystemService getEcosystemService() {
        return ecosystemService;
    }


}
