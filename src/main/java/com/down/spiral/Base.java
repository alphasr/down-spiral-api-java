package com.down.spiral;

public class Base {
    protected String  sessionId;
    protected LOGGERTYPE loggerType;

    public Base(String sessionId, LOGGERTYPE loggerType) {
        this.sessionId = sessionId;
        this.loggerType = loggerType;
    }
}
