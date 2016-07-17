package de.uni_due.paluno.elefant.monitoring.service.controller;

import de.uni_due.paluno.elefant.monitoring.service.model.Metric;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Ole Meyer
 */
public class MeasurementThread extends Timer {

    private static final Map<String,MeasurementThread> timerMap=new HashMap<>();

    public static void measure(Metric mp){
        if(!timerMap.containsKey(mp.getId()))timerMap.put(mp.getId(),new MeasurementThread());
        timerMap.get(mp.getId()).setMetric(mp);
    }

    private Metric metric;
    private TimerTask timerTask;


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
                System.out.println(metric.getId());
            }
        };
        this.scheduleAtFixedRate(timerTask,100,metric.getInterval());
    }
}
