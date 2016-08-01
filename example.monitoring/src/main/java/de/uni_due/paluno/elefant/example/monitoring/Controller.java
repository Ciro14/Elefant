package de.uni_due.paluno.elefant.example.monitoring;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * @author Ole Meyer
 */

@RestController
public class Controller {

    private long lastTime;
    private long lastCount;
    private boolean firstRequest=true;

    @RequestMapping(value = "/sessions")
    public double getSessions() throws IOException {
        readCsv();
        if(firstRequest){
            lastTime=System.currentTimeMillis();
            lastCount=readCsv();
            firstRequest=false;
            return -1;
        }else{
            long lastTimeTemp=System.currentTimeMillis();
            long lastCountTemp=readCsv();


            double lc=lastCount;
            double lcTemp=lastCountTemp;
            double lt=lastTime;
            double ltTemp=lastTimeTemp;

            double val=(lcTemp-lc)/((ltTemp-lt)/1000);

            lastTime=lastTimeTemp;
            lastCount=lastCountTemp;
            return val;
        }
    }

    private long readCsv() throws IOException {
        URL url = new URL ("http://10.8.100.83/haproxy?stats;csv");
        String encoding = Base64.getEncoder().encodeToString("paluno:password".getBytes());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty  ("Authorization", "Basic " + encoding);
        InputStream content = (InputStream)connection.getInputStream();

        CSVParser csvParser= CSVFormat.RFC4180.withHeader().parse(new InputStreamReader(content));
        CSVRecord record=csvParser.getRecords().get(4);
        long val=Long.valueOf(record.get("stot"));
        connection.disconnect();
        return val;
    }
}
