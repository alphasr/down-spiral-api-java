package com.down.spiral;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.*;

public class DownSpiral {


    private static HashMap<String, Base> logs = new HashMap<String, Base>();
    private static LOGLEVELS filter;
    private static final Logger logger = Logger.getLogger(DownSpiral.class);
    private static Visualize visualize = new Visualize();

    public static void closeConnection() throws InterruptedException {
        visualize.close();
        logs.clear();

    }

    public static void configure_logger(String host, int port,String id,LOGGERTYPE logType){
       visualize.initialize(host,port);

        if(logType == LOGGERTYPE.SIMPLE_PRINTER){
            Logs newLog = new Logs(id,logType);
            logs.put(id,newLog);
        }
        else if(logType == LOGGERTYPE.TABLE_PRINTER){
            Table table = new Table(id,logType);
            logs.put(id,table);
        }
        else if(logType == LOGGERTYPE.HTML_PRINTER){
            HTML html = new HTML(id,logType);
            logs.put(id,html);
        }
        else{
            logger.info("Couldn't configured with type "+logType);

        }
        logger.info("successfully configured with type "+logType);

    }

    public static void configure_logger(String host, int port,String id,LOGGERTYPE logType,int level){
        visualize.initialize(host,port);

        if(logType == LOGGERTYPE.SIMPLE_PRINTER){
            Logs newLog = new Logs(id,logType);
            logs.put(id,newLog);
        }
        logger.info("successfully configured with type "+logType);
    }

    public static void configure_logger(String host, int port,String id,LOGGERTYPE logType,String graphType,String x_axis,String y_axis){
        visualize.initialize(host,port);

        if(logType == LOGGERTYPE.GRAPH_PRINTER){
            Graph newGraph = new Graph(id,logType,graphType,x_axis,y_axis);
            logs.put(id,newGraph);
        }
        logger.info("successfully configured with type "+logType);
    }


    public static void log(String sessionId, String resultLabel,String result) throws JSONException, InterruptedException {
        Base temp = logs.get(sessionId);
        if (temp == null) {
            logger.error("Session doesn't exists,check configuration.");
            return;
        }
        if (!(temp instanceof Logs)) {
            logger.error("Session doesn't exists,check configuration.");
            return;

        }

        if (LOGLEVELS.LOG >= ((Logs) temp).getFilter()) {

                JsonObject jsonObject = new JsonObject();
                JsonArray jsonArray = new JsonArray();
                JsonObject tempJsonObject = new JsonObject();

                UUID uuid= UUID.randomUUID();
                jsonObject.addProperty("sessionId",sessionId);
                tempJsonObject.addProperty("resultLabel",resultLabel);
                tempJsonObject.addProperty("id",uuid.toString());
                tempJsonObject.addProperty("resultValue",result);
                jsonArray.add(tempJsonObject);
                jsonObject.add("data",jsonArray);
                visualize.sendLogs(jsonObject,temp.loggerType);
            }




    }

    public static void log_all(String sessionId, ArrayList<HashMap<String,String>>  tableData) throws InterruptedException, JSONException {
        Base temp = logs.get(sessionId);
        if (temp == null) {
            logger.error("Session doesn't exists,check configuration.");
            return;
        }

        if (!(temp instanceof Table)) {
            logger.error("Session doesn't exists,check configuration.");
            return;

        }
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        jsonObject.addProperty("sessionId",sessionId);
            for(int i = 0;i<tableData.size();i++){
                HashMap<String, String> tmpData = (HashMap<String, String>) tableData.get(i);
                JsonObject tempJsonObject = new JsonObject();

                for (Map.Entry<String, String> entry : tmpData.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    tempJsonObject.addProperty(key,value);
                }
                jsonArray.add(tempJsonObject);

            }

        jsonObject.add("rowData",jsonArray);

        visualize.sendLogs(jsonObject,temp.loggerType);
        visualize.close();
    }


    public static void log(String sessionId, HashMap<String,String> rowData)throws  InterruptedException {
        Base temp = logs.get(sessionId);
        if (temp == null) {
            logger.error("Session doesn't exists,check configuration.");
            return;
        }

        if (!(temp instanceof Table)) {
            logger.error("Session doesn't exists,check configuration.");
            return;

        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sessionId",sessionId);
        JsonArray jsonArray = new JsonArray();
        JsonObject tempJsonObject = new JsonObject();

        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            tempJsonObject.addProperty(key,value);
        }
        jsonArray.add(tempJsonObject);

        jsonObject.add("rowData",jsonArray);

        visualize.sendLogs(jsonObject,temp.loggerType);

    }

    public static void log(String sessionId,String data)throws  InterruptedException {
        Base temp = logs.get(sessionId);
        if (temp == null) {
            logger.error("Session doesn't exists,check configuration.");
            return;
        }

        if (!(temp instanceof HTML)) {
            logger.error("Session doesn't exists,check configuration.");
            return;

        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sessionId",sessionId);
        jsonObject.addProperty("html",data);
        visualize.sendLogs(jsonObject,temp.loggerType);



    }

    public static void log(String sessionId, HashMap<String,String> graphData,String label)throws  InterruptedException {

        Base temp = logs.get(sessionId);
        if (temp == null) {
            logger.error("Session doesn't exists,check configuration.");
            return;
        }

        if (!(temp instanceof Graph)) {
            logger.error("Session doesn't exists,check configuration.");
            return;
        }

        JsonObject jsonObject;
        if(((Graph) temp).getGraphType() == GRAPHTYPES.SCATTER ){
            jsonObject = processGraphScatter(graphData, temp, sessionId, label);
        }
        else if(((Graph) temp).getGraphType() == GRAPHTYPES.BUBBLE){
            jsonObject = processGraphBubble(graphData, temp, sessionId, label);

        }
      else{
            jsonObject = processGraph(graphData, temp, sessionId, label);

        }
        visualize.sendLogs(jsonObject,temp.loggerType);

    }

    static JsonObject processGraph(HashMap<String, String> graphData, Base temp, String sessionId, String label){
        TreeMap<String, String> treeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll(graphData);

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        jsonObject.addProperty("type",((Graph) temp).getGraphType());

        jsonObject.addProperty("sessionId",sessionId);

        jsonArray.add((treeMap.get(((Graph) temp).getY_axis())));
        jsonObject.add("labels",jsonArray);

        JsonObject tempJsonObject = new JsonObject();
        JsonArray tempJsonArray = new JsonArray();

        tempJsonObject.addProperty("label",label);

        tempJsonArray.add((treeMap.get(((Graph) temp).getX_axis())));
        tempJsonObject.add("data",tempJsonArray);

        jsonObject.add("datasets",tempJsonObject);
        return jsonObject;
    }

    static JsonObject processGraphScatter(HashMap<String, String> graphData, Base temp, String sessionId, String label){
        TreeMap<String, String> treeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll(graphData);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type",((Graph) temp).getGraphType());
        jsonObject.addProperty("sessionId",sessionId);

            JsonArray jsonArray = new JsonArray();
            JsonObject tempJsonObject = new JsonObject();
            tempJsonObject.addProperty("x",(treeMap.get(((Graph) temp).getY_axis())));
            tempJsonObject.addProperty("y",(treeMap.get(((Graph) temp).getY_axis())));
            jsonArray.add(tempJsonObject);

        JsonObject tempJsonObjectNew = new JsonObject();
        tempJsonObjectNew.addProperty("label",label);
        tempJsonObjectNew.add("data",jsonArray);
        jsonObject.add("datasets",tempJsonObjectNew);
        jsonObject.add("labels",new JsonArray());
        return jsonObject;

    }

    static JsonObject processGraphBubble(HashMap<String, String> graphData, Base temp, String sessionId, String label){
        TreeMap<String, String> treeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll(graphData);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type",((Graph) temp).getGraphType());
        jsonObject.addProperty("sessionId",sessionId);

            JsonArray jsonArray = new JsonArray();
            JsonObject tempJsonObject = new JsonObject();
            tempJsonObject.addProperty("x",(treeMap.get(((Graph) temp).getY_axis())));
            tempJsonObject.addProperty("y",(treeMap.get(((Graph) temp).getY_axis())));
            tempJsonObject.addProperty("r",20);
            jsonArray.add(tempJsonObject);

        JsonObject tempJsonObjectNew = new JsonObject();
        tempJsonObjectNew.addProperty("label",label);
        tempJsonObjectNew.add("data",jsonArray);
        jsonObject.add("datasets",tempJsonObjectNew);
        jsonObject.add("labels",new JsonArray());
        return jsonObject;

    }


}