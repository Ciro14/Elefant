package com.olemeyer.uni.sas.genetic_optimization;

import com.google.gson.Gson;
import com.olemeyer.uni.sas.genetic_optimization.config.Configuration;
import com.olemeyer.uni.sas.genetic_optimization.execution.IndividualGenerator;
import com.olemeyer.uni.sas.genetic_optimization.model.Individual;
import com.olemeyer.uni.sas.genetic_optimization.model.SoftgoalTarget;
import de.uni_due.paluno.elefant.featuremodel_extended.Feature;
import de.uni_due.paluno.elefant.featuremodel_extended.Softgoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ole Meyer
 */
@RestController
public class RESTController {

    @Autowired
    private IndividualGenerator individualGenerator;

    @RequestMapping(value = "/optimize")
    public Feature optimize(@Valid  OptimizationRequest request){
        List<Individual> individuals=individualGenerator.generate(request.getModel(), request.getConfig().getIndividualCount());
        System.out.println(new Gson().toJson(individuals));
        return null;
    }


    private class OptimizationRequest{
        @NotNull
        private Feature model;
        private List<SoftgoalTarget> targets=new LinkedList<>();
        private Configuration config;

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

}
