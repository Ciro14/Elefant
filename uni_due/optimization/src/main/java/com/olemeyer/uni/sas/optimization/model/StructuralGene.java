package com.olemeyer.uni.sas.optimization.model;

import de.uni_due.paluno.elefant.featuremodel_extended.Feature;
import de.uni_due.paluno.elefant.featuremodel_extended.Softgoal;

import java.util.Random;

/**
 * @author Ole Meyer
 * This class representds a structural gene. It exists an instance of this class for each feature of the system tha can be sitched on
 * and off.
 */
public class StructuralGene implements Gene {

    private static final Random random=new Random();

    private boolean active;
    private Feature feature;

    public StructuralGene(Feature feature) {
        this.feature = feature;
        this.active= true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
