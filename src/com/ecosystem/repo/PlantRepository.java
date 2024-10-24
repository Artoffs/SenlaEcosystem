package com.ecosystem.repo;

import com.ecosystem.models.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantRepository {
    private final List<Plant> plants;

    public PlantRepository() {
        this.plants = new ArrayList<>();
    }

    public void createPlant(Plant plant) {
        plants.add(plant);
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void updatePlant(Plant plant) {
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getId().equals(plant.getId())) {
                plants.set(i, plant);
                break;
            }
        }
    }

    public void deletePlant(Plant plant) {
        plants.remove(plant);
    }

}
