package de.uni_due.paluno.elefant.monitoring.service.controller;

import com.sun.management.OperatingSystemMXBean;
import de.uni_due.paluno.elefant.monitoring.service.db.MeasurementRepository;
import de.uni_due.paluno.elefant.monitoring.service.model.Measurement;
import de.uni_due.paluno.elefant.monitoring.service.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerConnection;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

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

                URL url = null;
                try {
                    url = new URL(metric.getUrl());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoOutput(true);
                    InputStream content = (InputStream)connection.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(content));
                    String response=br.readLine();
                    br.close();
                    double val=Double.valueOf(response);
                    Measurement measurement=new Measurement(metric.getId(),val);
                    measurementRepository.insert(measurement);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        this.scheduleAtFixedRate(timerTask,100,metric.getInterval());
    }
}
