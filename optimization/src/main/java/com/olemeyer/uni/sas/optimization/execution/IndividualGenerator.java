package com.olemeyer.uni.sas.optimization.execution;

import com.olemeyer.uni.sas.optimization.model.Individual;
import com.olemeyer.uni.sas.optimization.model.RegulatoryGene;
import com.olemeyer.uni.sas.optimization.model.StructuralGene;
import com.olemeyer.uni.sas.optimization.utils.Utils;
import de.uni_due.paluno.elefant.featuremodel.Feature;
import de.uni_due.paluno.elefant.featuremodel.Softgoal;
import de.uni_due.paluno.elefant.featuremodel.SoftgoalImpact;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ole Meyer
 */
@Component
public class IndividualGenerator {

    /**
     * This method create a list of individuals based on a feature model.
     * @param model
     * @param count The size of the generated list.
     * @return
     */
    public List<Individual> generate(Feature model, int count){
        //Get a flat list of all softgoals for creating the regulatory genes
        List<Softgoal> allSoftgoals= Utils.getAllSoftgoals(model);
        //Get a flat list of all features for creating the structural genes
        List<Feature> allFeatures= Utils.getAllFeatures(model);

        //Create the individuals
        List<Individual> individuals=new LinkedList<>();
        for(int i=0;i<count;++i){
            //Create regulatory genes
            List<RegulatoryGene> regulatoryGenes=new LinkedList<>();
            for(Softgoal softgoal:allSoftgoals){
                regulatoryGenes.add(new RegulatoryGene(softgoal));
            }
            //Create structural genes
            List<StructuralGene> structuralGenes=new LinkedList<>();
            for(Feature feature:allFeatures){
                structuralGenes.add(new StructuralGene(feature));
            }
            //Create connections between the regulatory and structural genes
            Map<String,RegulatoryGene> softgoalToRegulatoryGeneMap=new HashMap<>();
            for(RegulatoryGene rg:regulatoryGenes)softgoalToRegulatoryGeneMap.put(rg.getSoftgoal().getUuid(),rg);
            for(StructuralGene sg:structuralGenes){
                for(SoftgoalImpact sgimpact:sg.getFeature().getSoftgoalImpacts()){
                    softgoalToRegulatoryGeneMap.get(sgimpact.getUuid()).getConnectedStructuralGenes().add(sg);
                }
            }

            //Create individual
            Individual individual=new Individual(regulatoryGenes,structuralGenes);

            //Add individual to list
            individuals.add(individual);

        }
        return individuals;
    }


}
