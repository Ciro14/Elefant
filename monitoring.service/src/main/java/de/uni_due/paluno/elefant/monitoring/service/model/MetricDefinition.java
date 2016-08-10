package de.uni_due.paluno.elefant.monitoring.service.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ole Meyer
 */

@Document(collection = "metrics")
public class MetricDefinition {
    @Field(value = "time")
    private long time;
    @Field(value = "metrics")
    private List<Metric> metrcis;

    public MetricDefinition(long time, List<Metric> metrcis) {
        this.time = time;
        this.metrcis = metrcis;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
