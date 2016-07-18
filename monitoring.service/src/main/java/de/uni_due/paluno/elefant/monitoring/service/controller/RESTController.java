package de.uni_due.paluno.elefant.monitoring.service.controller;

import de.uni_due.paluno.elefant.monitoring.service.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author Ole Meyer
 */

@RestController
public class RESTController {

    @Autowired
    private MeasurementThread measurementThread;

    @RequestMapping(value = "/metrics",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void metric(@RequestBody @Valid Metric[] metrics){
        for(Metric mp: metrics){
            measurementThread.measure(mp);
        }
    }
}
