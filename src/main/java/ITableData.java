import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class ITableData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("timestamp")
    @Expose
    private Date timestamp;
    @SerializedName("hostName")
    @Expose
    private String hostName;
    @SerializedName("appName")
    @Expose
    private String appName;
    @SerializedName("priority")
    @Expose
    private String priority;
    ITableData(String hostName, String appName, String priority){
        this.id = UUID.randomUUID().toString();
        this.timestamp = new Date();
        this.hostName = hostName;
        this.appName = appName;
        this.priority = priority;
    }
}
