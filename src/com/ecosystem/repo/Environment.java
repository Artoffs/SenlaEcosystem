package com.ecosystem.repo;

import com.ecosystem.models.ConditionType;
import com.ecosystem.models.ResourceType;
import com.ecosystem.utils.Event;
import com.ecosystem.utils.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Класс, который хранит ресурсы и условия экосистемы.
 */

public class Environment {
    private final Map<ConditionType, Double> conditions;
    private final Map<ResourceType, Double> resources;
    private int environmentHeight = 25;
    private int environmentWidth = 25;
    private final Random random;



    public Environment() {
        this.conditions = new HashMap<>();
        this.resources = new HashMap<>();
        conditions.put(ConditionType.TEMPERATURE, 20d);
        conditions.put(ConditionType.PRECIPITATION, 10d);
        conditions.put(ConditionType.HUMIDITY, 60d);
        resources.put(ResourceType.WATER, 800d);
        this.random = new Random();
    }

    public Environment(Map<ConditionType, Double> conditions, Map<ResourceType, Double> resources) {
        this.conditions = conditions;
        this.resources = resources;
        this.random = new Random();
    }

    public void updateConditions() {
        Logger.log(updateCondition(ConditionType.TEMPERATURE, random.nextDouble(3) - 1));
        Logger.log(updateCondition(ConditionType.HUMIDITY, random.nextDouble(3) - 1));
        Logger.log(updateCondition(ConditionType.PRECIPITATION, random.nextDouble(3) - 1));
        Logger.log(updateResource(ResourceType.WATER, random.nextDouble(getCondition(ConditionType.PRECIPITATION)) - 3));

        if(getResource(ResourceType.WATER) < 0) setResource(ResourceType.WATER, 0d);
        if(getCondition(ConditionType.TEMPERATURE) < 0) setCondition(ConditionType.TEMPERATURE, 0d);
        if(getCondition(ConditionType.TEMPERATURE) > 40) setCondition(ConditionType.TEMPERATURE, 40d);
        if(getCondition(ConditionType.HUMIDITY) < 0) setCondition(ConditionType.HUMIDITY, 0d);
        if(getCondition(ConditionType.HUMIDITY) > 100) setCondition(ConditionType.HUMIDITY, 100d);
        if(getCondition(ConditionType.PRECIPITATION) < 1) setCondition(ConditionType.HUMIDITY, 1d);
        if(getCondition(ConditionType.PRECIPITATION) > 15) setCondition(ConditionType.HUMIDITY, 15d);

    }

    public Double getCondition(ConditionType type) {
        return conditions.get(type);
    }

    public Event updateCondition(ConditionType type, Double value) {
        double newValue = conditions.get(type) + value;
        conditions.put(type, newValue);
        return new Event(String.format("%s изменился на %f", type, value));
    }

    public Double getResource(ResourceType type) {
        return resources.get(type);
    }

    public Event updateResource(ResourceType type, Double value) {
        double newValue = resources.get(type) + value;
        resources.put(type, newValue);
        return new Event(String.format("%s изменился на %f", type, value));
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

    public void setEnvironmentHeight(int environmentHeight) {
        this.environmentHeight = environmentHeight;
    }

    public void setEnvironmentWidth(int environmentWidth) {
        this.environmentWidth = environmentWidth;
    }
}
