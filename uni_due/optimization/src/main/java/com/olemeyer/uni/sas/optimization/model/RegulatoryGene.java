package com.olemeyer.uni.sas.optimization.model;

import de.uni_due.paluno.elefant.featuremodel_extended.Softgoal;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ole Meyer
 * This class represents a regulatory gene. In this case each instance of this class is connected with a specific softgoal.
 * The connected structural genes should be those who have an impact on this goal. As a result, the status of this regulatory gene
 * controlls how the connected softgoal is considered in the calculation of the fitness score.
 */
public class RegulatoryGene implements Gene {
    private boolean active;
    private Softgoal softgoal;
    private List<StructuralGene> connectedStructuralGenes=new LinkedList<>();

    public RegulatoryGene(Softgoal softgoal) {
        this.softgoal = softgoal;
        this.active=true;
    }

    public Softgoal getSoftgoal() {
        return softgoal;
    }

    public void setSoftgoal(Softgoal softgoal) {
        this.softgoal = softgoal;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<StructuralGene> getConnectedStructuralGenes() {
        return connectedStructuralGenes;
    }

    public void setConnectedStructuralGenes(List<StructuralGene> connectedStructuralGenes) {
        this.connectedStructuralGenes = connectedStructuralGenes;
    }
}
