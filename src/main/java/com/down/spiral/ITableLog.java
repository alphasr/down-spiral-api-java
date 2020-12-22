import java.util.Arrays;
import java.util.Vector;

class ITableLog{
     String[] header;
    private String sessionId;
     Vector <String[]> data = new Vector<>();

    ITableLog(String[] header,String sessionId){
        this.header = header;
        this.sessionId = sessionId;
    }


    boolean addLogs(String data[]){
        this.data.addElement(data);
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