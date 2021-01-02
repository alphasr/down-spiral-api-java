package com.down.spiral;

import com.google.gson.JsonObject;

public class Logs extends Base {

    private int loggerLevel;
    private int filter;
    Logs(String sessionId,LOGGERTYPE loggerType){
        super(sessionId, loggerType);
        this.loggerLevel= LOGLEVELS.LOG;
        this.filter= LOGLEVELS.NULL;
    }



    public int getFilter() {
        return filter;
    }
    public LOGGERTYPE getLoggerType() {
        return this.loggerType;
    }

    public void setFilter(int filter) {
        this.filter = filter;

    }

    public int getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(int loggerLevel) {
        this.loggerLevel = loggerLevel;

    }
}
