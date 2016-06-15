package de.uni_due.paluno.elefant.execution_service;

import de.uni_due.paluno.elefant.featuremodel_extended.Attribute;

/**
 * Created by Ole Meyer
 */
public class AttributeDiff extends Diff<Attribute,Double> {
    public AttributeDiff(Attribute target, Double diffValue) {
        super(target, diffValue);
    }
}
