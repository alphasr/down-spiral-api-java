package com.down.spiral;

import com.google.gson.JsonObject;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.apache.log4j.Logger;

import java.net.URISyntaxException;

class Visualize {

    private Socket socket;
    public final static String logDataFile = "src/logger.json";
    public final static String graphDataFile = "src/graph.json";
    private static final Logger logger = Logger.getLogger(DownSpiral.class);

    public void initialize(String host, int port) {
       if(this.socket == null || !this.socket.connected()){
           try {
               this.socket = IO.socket("http://localhost:8000");
           } catch (URISyntaxException e) {
               logger.error(e);
           }
       }

    }
    public void close() throws InterruptedException{

        while(this.socket.connected()){
            this.socket.disconnect();
            Thread.sleep(1000);
        }

    }




    void sendLogs(JsonObject logs, LOGGERTYPE loggerType) throws InterruptedException {
        
        while(!this.socket.connected()){
            this.socket.connect();
            Thread.sleep(1000);
        }
        this.socket.emit((loggerType).toString(), logs);


    }

    void sendLogs(String rowData, LOGGERTYPE loggerType) throws InterruptedException {

        while(!this.socket.connected()){
            this.socket.connect();
            Thread.sleep(1000);
        }
        this.socket.emit((loggerType).toString(), rowData);


    }



}