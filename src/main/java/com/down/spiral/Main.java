package com.down.spiral;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.down.spiral.DownSpiral.*;


public class Main {
    public static void main(String[] args) throws InterruptedException, JSONException {

        String id= "results";
        configure_logger("localhost", 8000, id, LOGGERTYPE.TABLE_PRINTER);
        String[] temp ={"polo","rolo","kolo","jj","kfing"};
          ArrayList<HashMap<String,String>> results = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
        HashMap<String, String> logs = new HashMap<String, String>();

        if (i%2 == 0) {
        logs.put("i",i+"");
        logs.put("result",temp[i]);
        results.add(logs);

        } else {
        logs.put("i",i+"");
        logs.put("result",temp[i]);
        logs.put("elaspsed",temp[i]);
        results.add(logs);
        }
        }

        log_all("results",results);


        closeConnection();


    }




}



//    configure_logger("localhost", 3000, "results", SIMPLE_PRINTER, LogLevel.WARN)
//
//    configure_logger("localhost", 3000, "results", SIMPLE_PRINTER)
//
//result_label := concat("result ", i)
//        result_line = concat(result_label, ": ", result)
//        log("results", result_line)