package com.ecosystem.repo;

import com.ecosystem.models.ConditionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConditionsRepository {
    private final Map<ConditionType, Double> conditions;

    public ConditionsRepository() {
        this.conditions = new HashMap<>();
    }

    public void createCondition(ConditionType type, Double value)
    {
        conditions.put(type, value);
    }

    public Map<ConditionType, Double> getConditions() {
        return conditions;
    }

    public Map.Entry<ConditionType, Double> getCondition(ConditionType type) {
        Optional<Map.Entry<ConditionType, Double>> conditionEntry = conditions.entrySet().stream()
                .filter(entry -> entry.getKey().equals(type))
                .findFirst();
        return conditionEntry.orElseThrow();
    }

    public void updateCondition(ConditionType type, Double newValue) {
        if(conditions.containsKey(type)) {
            conditions.put(type, newValue);
        }
    }

    public void deleteCondition(ConditionType type) {
        conditions.remove(type);
    }
}
