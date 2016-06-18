package de.uni_due.paluno.elefant.executor;

import de.uni_due.paluno.elefant.featuremodel.Attribute;

/**
 * Created by Ole Meyer
 */
public class AttributeDiff extends Diff<Attribute,Double> {
    public AttributeDiff(Attribute target, Double diffValue) {
        super(target, diffValue);
    }
}
