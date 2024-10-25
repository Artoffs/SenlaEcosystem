package com.ecosystem.simulation;

import com.ecosystem.services.EcosystemService;

public class Simulation {
    private final EcosystemService ecosystemService;

    public Simulation(EcosystemService ecosystemService) {
        this.ecosystemService = ecosystemService;
    }

    public Simulation() {
        this.ecosystemService = new EcosystemService();
    }

    public EcosystemService getEcosystemService() {
        return ecosystemService;
    }


}
