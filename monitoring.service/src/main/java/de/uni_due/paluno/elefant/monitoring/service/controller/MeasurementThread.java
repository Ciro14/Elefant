package de.uni_due.paluno.elefant.monitoring.service.controller;

import de.uni_due.paluno.elefant.monitoring.service.db.MeasurementRepository;
import de.uni_due.paluno.elefant.monitoring.service.model.Measurement;
import de.uni_due.paluno.elefant.monitoring.service.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Ole Meyer
 */
@Component
public class MeasurementThread extends Timer {

    private final Map<String,MeasurementThread> timerMap=new HashMap<>();

    @Autowired
    private MeasurementRepository measurementRepository;

    public void measure(Metric mp){
        if(!timerMap.containsKey(mp.getId()))timerMap.put(mp.getId(),new MeasurementThread(measurementRepository));
        timerMap.get(mp.getId()).setMetric(mp);
    }

    private Metric metric;
    private TimerTask timerTask;

    public MeasurementThread(){}
    public MeasurementThread(MeasurementRepository measurementRepository){
        this.measurementRepository=measurementRepository;
    }


    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        System.out.println(metric);
        this.metric = metric;
        if(this.timerTask!=null){
            timerTask.cancel();
            this.purge();
        }
        this.timerTask=new TimerTask() {
            @Override
            public void run() {
                //TODO Impement Http Call
                Measurement measurement=new Measurement(metric.getId(),Math.random()*100);
                measurementRepository.insert(measurement);
            }
        };
        this.scheduleAtFixedRate(timerTask,100,metric.getInterval());
    }
}
