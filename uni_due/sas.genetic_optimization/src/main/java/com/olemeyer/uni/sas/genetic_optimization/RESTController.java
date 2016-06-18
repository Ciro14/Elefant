package com.olemeyer.uni.sas.genetic_optimization;

import com.google.gson.Gson;
import com.olemeyer.uni.sas.genetic_optimization.config.Configuration;
import com.olemeyer.uni.sas.genetic_optimization.execution.IndividualGenerator;
import com.olemeyer.uni.sas.genetic_optimization.model.Individual;
import com.olemeyer.uni.sas.genetic_optimization.model.SoftgoalTarget;
import de.uni_due.paluno.elefant.featuremodel_extended.Feature;
import de.uni_due.paluno.elefant.featuremodel_extended.Softgoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/optimize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Feature optimize(@Valid @RequestBody OptimizationRequest request){
        List<Individual> individuals=individualGenerator.generate(request.getModel(), request.getConfig().getIndividualCount());
        System.out.println(new Gson().toJson(individuals));
        return null;
    }


}
