import java.util.Arrays;
import java.util.Vector;

class ITableLog{
     String[] header;
    private String sessionId;
     Vector <ITableData> data = new Vector<>();

    ITableLog(String[] header,String sessionId){
        this.header = header;
        this.sessionId = sessionId;
    }


    boolean addLogs(String hostName, String appName, String priority){

        ITableData object = new ITableData(hostName,appName,priority);
        data.addElement(object);
        return true;

    }

    void setHeader(String[] header){
        flushHeader();
        this.header = header;

    }

    void setsessionId(String sessionId){
        this.sessionId = sessionId;

    }

    void flushData(){
        //flush data
        data.clear();
    }

    void flushHeader(){
        //flush header string
        header = new String[0];
    }



}