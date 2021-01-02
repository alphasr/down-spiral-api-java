package com.down.spiral;

class Graph extends Base{
    private String graphType;
    private String x_axis;
    private String y_axis;
    public Graph(String sessionId, LOGGERTYPE loggerType,String graphType,String x_axis,String y_axis) {
        super(sessionId, loggerType);
        this.graphType = graphType;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public String getX_axis() {
        return x_axis;
    }

    public String getY_axis() {
        return y_axis;
    }

    public String getGraphType() {
        return graphType;
    }
}