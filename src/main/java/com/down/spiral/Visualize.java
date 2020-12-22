import com.google.gson.Gson;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Visualize {

    private Socket socket;
    public final static String SOCKET_PORT = "8000";
    public final static String logDataFile = "src/logger.json";
    public final static String graphDataFile = "src/graph.json";
    public final static String hostName = "http://localhost:";

    public Visualize() {
        try {
            this.socket = IO.socket(hostName+SOCKET_PORT);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    void setLogs(ITableLog payload, boolean flush) {

        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get(logDataFile));
            // convert logs object to JSON file
            gson.toJson(payload, writer);
            //flush data
            payload.flushData();
            writer.close();
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }

        if(flush){
            payload.flushHeader();
            payload.flushData();
        }
    }

     void setGraph(Graph payload, boolean flush) {

        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get(graphDataFile));
            // convert logs object to JSON file
            gson.toJson(payload, writer);
            //flush data
//            payload.flushData();
            writer.close();
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }

         if(flush){
            payload.flushDatasets();
            payload.flushLabels();
         }

    }


     void sendData (String tableData) throws  InterruptedException {
         socket.emit("tableData", tableData);
         socket.connect();
         while(!socket.connected()){
             Thread.sleep(1000);
         }
         socket.disconnect();
    }

     void sendGraph (String graphData) throws  InterruptedException {
         socket.emit("graphData", graphData);
         socket.connect();
         while(!socket.connected()){
             Thread.sleep(1000);
         }
         socket.disconnect();
     }

     String getTableData(){
        Gson gson = new Gson();
        try(Reader reader = new FileReader(logDataFile)){
            ITableLog finalData = gson.fromJson(reader, ITableLog.class);
            return gson.toJson(finalData);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        return null;

    }
     String getGraphData(){
        Gson gson = new Gson();
        try(Reader reader = new FileReader(graphDataFile)){
            Graph finalData = gson.fromJson(reader, Graph.class);
            return gson.toJson(finalData);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

}
