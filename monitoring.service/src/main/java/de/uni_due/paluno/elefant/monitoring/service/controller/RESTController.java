package de.uni_due.paluno.elefant.monitoring.service.controller;

import de.uni_due.paluno.elefant.monitoring.service.db.MetricRepository;
import de.uni_due.paluno.elefant.monitoring.service.model.Metric;
import de.uni_due.paluno.elefant.monitoring.service.model.MetricDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;


/**
 * @author Ole Meyer
 */

@RestController
public class RESTController {

    @Autowired
    private MeasurementThread measurementThread;

    @Autowired
    private MetricRepository metricRepository;

    @RequestMapping(value = "/metrics",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void metric(@RequestBody @Valid Metric[] metrics){

        for(Metric mp: metrics){
            measurementThread.measure(mp);
        }
        MetricDefinition md=new MetricDefinition(System.currentTimeMillis(), Arrays.asList(metrics));
        metricRepository.insert(md);

    }
}
