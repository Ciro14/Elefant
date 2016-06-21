package com.olemeyer.uni.sas.optimization.config;

import org.springframework.stereotype.Component;

/**
 * Created by Ole Meyer
 */
@Component
public class Configuration {
    private int individualCount;

    public int getIndividualCount() {
        return individualCount;
    }

    public void setIndividualCount(int individualCount) {
        this.individualCount = individualCount;
    }
}
