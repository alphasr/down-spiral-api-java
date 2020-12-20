import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Vector;

public class GraphData {
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("data")
    @Expose
    Vector<Integer> data;


    GraphData(String label, Vector<Integer> data){
        this.label =label;
        this.data = data;
    }
}
