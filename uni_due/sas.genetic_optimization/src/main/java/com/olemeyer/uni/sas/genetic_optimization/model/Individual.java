package com.olemeyer.uni.sas.genetic_optimization.model;

import java.util.List;

/**
 * @author Ole Meyer
 */
public class Individual {
    private List<RegulatoryGene> regulatoryGenes;
    private List<StructuralGene> structuralGenes;

    public Individual(List<RegulatoryGene> regulatoryGenes, List<StructuralGene> structuralGenes) {
        this.regulatoryGenes = regulatoryGenes;
        this.structuralGenes = structuralGenes;
    }

    public List<RegulatoryGene> getRegulatoryGenes() {
        return regulatoryGenes;
    }

    public void setRegulatoryGenes(List<RegulatoryGene> regulatoryGenes) {
        this.regulatoryGenes = regulatoryGenes;
    }

    public List<StructuralGene> getStructuralGenes() {
        return structuralGenes;
    }

    public void setStructuralGenes(List<StructuralGene> structuralGenes) {
        this.structuralGenes = structuralGenes;
    }
}
