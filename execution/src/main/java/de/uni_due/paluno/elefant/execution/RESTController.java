package de.uni_due.paluno.elefant.execution;

import de.uni_due.paluno.elefant.featuremodel.Feature;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ole Meyer
 */

@RestController
public class RESTController {


    private ModelStorage modelStorage=new ModelStorage();
    private DiffCreator diffCreator=new DiffCreator();
    private ActivityScheduler activityScheduler=new ActivityScheduler();
    private ActivityExecutor activityExecutor=new ActivityExecutor();

    @RequestMapping(value = "/model", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setModel(@Valid @RequestBody Feature rootFeature){
        modelStorage.setRootFeature(rootFeature);
    }

    @RequestMapping(value = "/model", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ActivityContainer>updateModel(@Valid @RequestBody Feature rootFeature, @RequestParam(required = false, value = "execute") boolean execute){
        List<FeatureDiff> featureDiffs=diffCreator.extractFeatureDiffs(modelStorage,new ModelStorage(rootFeature));
        List<ActivityContainer> activities=activityScheduler.schedule(featureDiffs,modelStorage);
        if(execute)activityExecutor.execute(activities);
        return activities;
    }

}
