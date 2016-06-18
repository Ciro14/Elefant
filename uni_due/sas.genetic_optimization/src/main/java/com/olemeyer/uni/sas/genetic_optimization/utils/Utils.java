package com.olemeyer.uni.sas.genetic_optimization.utils;

import de.uni_due.paluno.elefant.featuremodel_extended.Feature;
import de.uni_due.paluno.elefant.featuremodel_extended.FeatureConnection;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ole Meyer
 */
public class Utils {
    public static List<Feature> getAllFeatures(Feature feature){
        List<Feature> features=new LinkedList<>();
        features.add(feature);
        for(FeatureConnection featureConnection:feature.getFeatureConnections()){
            for(Feature f:featureConnection.getFeatures())features.addAll(getAllFeatures(f));
        }
        return features;
    }
}
