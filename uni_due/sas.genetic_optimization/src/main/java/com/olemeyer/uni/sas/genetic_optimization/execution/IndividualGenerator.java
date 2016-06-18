package com.olemeyer.uni.sas.genetic_optimization.execution;

import com.olemeyer.uni.sas.genetic_optimization.model.Individual;
import com.olemeyer.uni.sas.genetic_optimization.utils.Utils;
import de.uni_due.paluno.elefant.featuremodel_extended.Feature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author
 */
@Component
public class IndividualGenerator {

    public List<Individual> generate(Feature model, int count){
        List<Feature> allFeatures= Utils.getAllFeatures(model);

        return null;
    }

}
