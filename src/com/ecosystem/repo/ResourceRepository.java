package com.ecosystem.repo;

import com.ecosystem.models.Condition;
import com.ecosystem.models.ConditionType;
import com.ecosystem.models.Resource;
import com.ecosystem.models.ResourceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ResourceRepository {
    private final List<Resource> resources;

    public ResourceRepository() {
        this.resources = new ArrayList<>();
        initializeResources();
    }

    public ResourceRepository(List<Resource> resources) {
        this.resources = resources;
    }

    private void initializeResources() {
        // Добавляем начальные условия в список
        resources.add(new Resource(ResourceType.WATER, 8000d));
    }

    // C
    public void createResource(Resource resource) {
        Resource newRes;
        if ((newRes = this.getResource(resource.getType())) != null) {
            newRes.setValue(resource.getValue());
            return;
        }
        resources.add(resource);
    }

    public List<Resource> getResources() {
        return resources;
    }


    public Resource getResource(ResourceType type) {
        Optional<Resource> optionalValue = resources.stream()
                .filter(resource -> resource.getType().equals(type))
                .findFirst();
        return optionalValue.orElseThrow();
    }

    public void setResource(ResourceType type, Double value) {
        resources.stream()
                .filter(resource -> resource.getType().equals(type))
                .forEach(resource -> resource.setValue(value));
    }

    public void deleteResource(Resource resource) {
        resources.remove(resource);
    }
}
