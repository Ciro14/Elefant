package com.olemeyer.uni.sas.optimization.utils;

import de.uni_due.paluno.elefant.featuremodel_extended.Feature;
import de.uni_due.paluno.elefant.featuremodel_extended.FeatureConnection;
import de.uni_due.paluno.elefant.featuremodel_extended.Softgoal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ole Meyer
 */
public class Utils {

    /**
     * This method creates a flat list of all features in the model
     * @param model
     * @return
     */
    public static List<Feature> getAllFeatures(Feature model){
        List<Feature> features=new LinkedList<>();
        features.add(model);
        for(FeatureConnection featureConnection:model.getFeatureConnections()){
            for(Feature feature:featureConnection.getFeatures())features.addAll(getAllFeatures(feature));
        }
        return features;
    }

    /**
     * This method creates a list of all softgoals in the model. Normally, all softgoals should be added to the root element,
     * but for error prevention this method will nevertheless iterate through all subfeatures
     * @param model
     * @return
     */
    public static List<Softgoal> getAllSoftgoals(Feature model){
        List<Softgoal> softgoals=new LinkedList<>();
        softgoals.addAll(model.getSoftgoals());
        for(FeatureConnection featureConnection:model.getFeatureConnections()){
            for(Feature feature:featureConnection.getFeatures())softgoals.addAll(getAllSoftgoals(feature));
        }
        return softgoals;
    }
}
