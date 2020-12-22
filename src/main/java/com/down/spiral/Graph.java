import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Vector;

public class Graph {
    @SerializedName("labels")
    @Expose
    private String labels[];
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("datasets")
    @Expose
    GraphData datasets;
    @SerializedName("type")
    @Expose
    private String type;


    Graph(String[] headers, String sessionID,String type){
        this.type =  type;
        this.labels = headers;
        this.sessionId = sessionID;
    }

   void setData(String label,Vector<Integer> data){
       this.datasets = new GraphData(label,data);
    }

    void setType (String type){
        this.type=  type;
    }
    void setLabels (String labels[]){
        this.labels=  labels;
    }
    void setSessionId (String sessionId){
        this.sessionId=  sessionId;
    }

    void flushDatasets(){
        //flush data
        datasets.data.clear();

    }
    void flushLabels(){
        //flush labels
        this.labels = new String[0];

    }
}
