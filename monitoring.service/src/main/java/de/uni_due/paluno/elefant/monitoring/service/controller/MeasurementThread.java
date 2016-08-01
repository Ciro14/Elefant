package de.uni_due.paluno.elefant.monitoring.service.controller;

import com.sun.management.OperatingSystemMXBean;
import de.uni_due.paluno.elefant.monitoring.service.db.MeasurementRepository;
import de.uni_due.paluno.elefant.monitoring.service.model.Measurement;
import de.uni_due.paluno.elefant.monitoring.service.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;
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
                double value=0;
                MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

                try {
                    OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(
                            mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
                    if(metric.getId().equals("cpu")){
                       value=osMBean.getSystemCpuLoad()*100;
                    }else if(metric.getId().equals("ram")){
                        value= osMBean.getTotalPhysicalMemorySize()-osMBean.getFreePhysicalMemorySize();
                    }
                    Measurement measurement=new Measurement(metric.getId(),value);
                    measurementRepository.insert(measurement);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        this.scheduleAtFixedRate(timerTask,100,metric.getInterval());
    }
}
