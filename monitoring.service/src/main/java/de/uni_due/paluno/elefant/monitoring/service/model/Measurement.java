package de.uni_due.paluno.elefant.monitoring.service.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Ole Meyer
 */
@Document(collection = "measurements")
public class Measurement {
    @Field("id")
    private String id;
    @Field("value")
    private double value;
    @Field("time")
    private long time;

    public Measurement(String id, double value) {
        this.id = id;
        this.value = value;
        this.time=System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
