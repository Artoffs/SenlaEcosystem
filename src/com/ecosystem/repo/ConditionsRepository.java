package com.ecosystem.repo;

import com.ecosystem.models.Condition;
import com.ecosystem.models.ConditionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConditionsRepository {
    private final List<Condition> conditions;

    public ConditionsRepository() {
        this.conditions = new ArrayList<>();
    }

    public ConditionsRepository(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public void createCondition(Condition condition) {
        Condition newCond;
        if ((newCond = this.getCondition(condition.getType())) != null) {
            newCond.setValue(condition.getValue());
            return;
        }
        conditions.add(condition);
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Condition getCondition(ConditionType type) {
        Optional<Condition> optionalCondition = conditions.stream()
                .filter(condition -> condition.getType().equals(type))
                .findFirst();
        return optionalCondition.orElseThrow();
    }

    public void deleteCondition(Condition condition) {
        conditions.remove(condition);
    }
}
