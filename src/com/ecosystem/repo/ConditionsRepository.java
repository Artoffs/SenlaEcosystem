package com.ecosystem.repo;

import com.ecosystem.models.Condition;
import com.ecosystem.models.ConditionType;

import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ConditionsRepository {
    private final List<Condition> conditions;

    public ConditionsRepository() {
        this.conditions = new ArrayList<>();
    }

    public ConditionsRepository(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public void createCondition(Condition condition) {
        conditions.add(condition);
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Condition getCondition(UUID uuid) {
        Optional<Condition> optionalCondition = conditions.stream()
                .filter(condition -> condition.getId().equals(uuid))
                .findFirst();
        return optionalCondition.orElseThrow();
    }

    public void deleteCondition(Condition condition) {
        conditions.remove(condition);
    }
}
