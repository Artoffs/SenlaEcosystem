package com.ecosystem.repo;

import com.ecosystem.models.Plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс-репозиторий для сущности растения. Тут реализуются CRUD операции
 */

public class PlantRepository {
    private final List<Plant> plants;

    public PlantRepository() {
        this.plants = new CopyOnWriteArrayList<>();
    }

    public PlantRepository(List<Plant> plants) {
        this.plants = plants;
    }

    public void createPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant getPlant(UUID uuid) {
        Optional<Plant> optionalPlant = plants.stream()
                .filter(plant -> plant.getId().equals(uuid))
                .findFirst();
        return optionalPlant.orElseThrow();
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

    public void updatePlants(List<Plant> newPlants) {
        this.plants.addAll(newPlants);
    }

    public void deleteEatenPlants(List<Plant> eatenPlants) {
        eatenPlants.forEach(this::deletePlant);
    }

    public void deletePlant(Plant plant) {
        plants.remove(plant);
    }
}
