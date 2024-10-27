package com.ecosystem.repo;

import com.ecosystem.models.ConditionType;
import com.ecosystem.models.ResourceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Environment {
    private final Map<ConditionType, Double> conditions;
    private final Map<ResourceType, Double> resources;
    private final static int environmentHeight = 100;
    private final static int environmentWidth = 100;
    private final Random random;



    public Environment() {
        this.conditions = new HashMap<>();
        this.resources = new HashMap<>();
        conditions.put(ConditionType.TEMPERATURE, 20d);
        conditions.put(ConditionType.PRECIPITATION, 10d);
        conditions.put(ConditionType.HUMIDITY, 60d);
        resources.put(ResourceType.WATER, 8000d);
        this.random = new Random();
    }

    public Environment(Map<ConditionType, Double> conditions, Map<ResourceType, Double> resources) {
        this.conditions = conditions;
        this.resources = resources;
        this.random = new Random();
    }

    public void updateConditions() {
        setCondition(ConditionType.TEMPERATURE, random.nextDouble(3) - 1);
        setCondition(ConditionType.HUMIDITY, random.nextDouble(3) - 1);
        setCondition(ConditionType.PRECIPITATION, random.nextDouble(3) - 1);
        setResource(ResourceType.WATER, random.nextDouble(getCondition(ConditionType.PRECIPITATION) - 5));

        if(getResource(ResourceType.WATER) < 0) setResource(ResourceType.WATER, 0d);
        if(getCondition(ConditionType.TEMPERATURE) < 0) setCondition(ConditionType.TEMPERATURE, 0d);
        if(getCondition(ConditionType.TEMPERATURE) > 30) setCondition(ConditionType.TEMPERATURE, 30d);
        if(getCondition(ConditionType.HUMIDITY) < 0) setCondition(ConditionType.HUMIDITY, 0d);
        if(getCondition(ConditionType.HUMIDITY) > 100) setCondition(ConditionType.HUMIDITY, 100d);
        if(getCondition(ConditionType.PRECIPITATION) < 0) setCondition(ConditionType.HUMIDITY, 0d);
        if(getCondition(ConditionType.PRECIPITATION) > 15) setCondition(ConditionType.HUMIDITY, 15d);

    }

    public Double getCondition(ConditionType type) {
        return conditions.get(type);
    }

    public Double getResource(ResourceType type) {
        return resources.get(type);
    }

    public void setCondition(ConditionType type, Double value) {
        conditions.put(type, value);
    }

    public void setResource(ResourceType type, Double value) {
        resources.put(type, value);
    }

    public Map<ConditionType, Double> getConditions() {
        return conditions;
    }

    public Map<ResourceType, Double> getResources() {
        return resources;
    }

    public int getHeight() {
        return environmentHeight;
    }

    public int getWidth() {
        return environmentWidth;
    }
}
