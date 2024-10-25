package com.ecosystem.repo;

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
    }

    public ResourceRepository(List<Resource> resources) {
        this.resources = resources;
    }

    // C
    public void createResource(Resource resource) {
        resources.add(resource);
    }

    public Resource getResourceValue(UUID id) {
        Optional<Resource> optionalValue = resources.stream()
                .filter(resource -> resource.getId().equals(id))
                .findFirst();
        return optionalValue.orElseThrow();
    }

    
}
