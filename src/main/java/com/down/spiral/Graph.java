package com.down.spiral;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class Graph {
    @SerializedName("labels")
    @Expose
    private String labels[];
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("type")
    @Expose
    private Types type;
    @SerializedName("datasets")
    @Expose
    private JsonObject datasets = new JsonObject();


    Graph(Types type, String sessionID){
        this.type =  type;
        this.sessionId = sessionID;
    }

    void addGraph(String label, String labels,String data){
        String[] tempLabels = labels.split(",");
        this.labels = tempLabels;

        datasets.addProperty("label",label);
        JsonArray datasetData = alter(data,tempLabels);

        datasets.add("data", datasetData);


    }

    JsonArray alter(String data, String[] labels){
        String [] tempData = data.split(",");
        JsonArray datasetData = new JsonArray();
        for(int i = 0; i < labels.length; i++){
            datasetData.add(tempData[i]);
        }

        return datasetData;

    }

//    {
//        labels: ["some", "new"],
//        datasets: {
//            data: ["33", "32"],
//            label: "some"
//        }
//    }




    void flushLabels(){
        //flush labels
        this.labels = new String[0];

    }
}
