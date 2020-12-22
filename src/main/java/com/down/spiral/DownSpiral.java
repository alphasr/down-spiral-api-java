package com.down.spiral;

public class DownSpiral {

        static private ITableLog payloadLogs;
        static private Graph payloadGraph;
        static private  Visualize obj = new Visualize();


   public static void initializeTable(String header, String sessionId){
            String[] tempHeader = header.split(",");
            ITableLog payload = new ITableLog(tempHeader, sessionId);
            payloadLogs = payload;
        }

    public static void initializeGraph(Types type, String sessionId){
        Graph payload = new Graph(type, sessionId);
        payloadGraph = payload;
    }

    public static void downSpiralGraphs(String label, String labels,String data){
            payloadGraph.addGraph(label,labels,data);
    }

    public static void downSpiralLogs(String data) throws InterruptedException {
            payloadLogs.addLogs(data);
    }

    public static void visualizeLogs() throws InterruptedException {
        obj.setLogs(payloadLogs, true);
        String tableData = obj.getTableData();
        obj.sendData(tableData);
   }

    public static void visualizeGraphs() throws InterruptedException {
        obj.setGraph(payloadGraph, true);
        String graphData = obj.getGraphData();
        obj.sendGraph(graphData);
    }


}
