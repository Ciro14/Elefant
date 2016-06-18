package com.olemeyer.uni.sas.genetic_optimization;

import com.olemeyer.uni.sas.genetic_optimization.config.Configuration;
import com.olemeyer.uni.sas.genetic_optimization.model.SoftgoalTarget;
import de.uni_due.paluno.elefant.featuremodel_extended.Feature;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ole Meyer
 */
public class OptimizationRequest {
    @NotNull
    private Feature model;
    private List<SoftgoalTarget> targets=new LinkedList<>();
    private Configuration config;

    public OptimizationRequest() {
    }

    public Feature getModel() {
        return model;
    }

    public void setModel(Feature model) {
        this.model = model;
    }

    public List<SoftgoalTarget> getTargets() {
        return targets;
    }

    public void setTargets(List<SoftgoalTarget> targets) {
        this.targets = targets;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
