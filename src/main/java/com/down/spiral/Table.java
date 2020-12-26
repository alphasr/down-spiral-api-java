package com.down.spiral;


import com.google.gson.JsonObject;

import java.util.Vector;

class ITableLog{
     String[] header;
     private String sessionId;
     Vector <JsonObject> data = new Vector<>();

    ITableLog(String[] header,String sessionId){
        this.header = changeCase(header);
        this.sessionId = sessionId;
    }
    String[] changeCase(String[] header){
        for (int i=0;i<header.length;i++){
            header[i] = header[i].toUpperCase();
        }
        return header;
    }

    boolean addLogs(String data){
        JsonObject temp = alter(data);
        this.data.addElement(temp);
        return true;
    }

    void setHeader(String[] header){
        flushHeader();
        this.header = header;

    }

    JsonObject alter(String data){
        String[] temp = data.split(",");
        JsonObject json = new JsonObject();
        for (int i = 0; i < header.length; i++) {
            json.addProperty(header[i].toLowerCase(), temp[i]);
        }
        return json;
   }

    void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }

    void flushData(){
        data.clear();
    }

    void flushHeader(){
        header = new String[0];
    }



}