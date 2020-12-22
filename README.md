# down-spiral-api-java

### Setup Java API

- Download the [JAR](https://github.com/alphasr/down-spiral-api-java/tree/master/out/artifacts/server_jar).

- Import the jar library in project.

### Usage

#### Logs visualization


- `initializeTable("ID,TIMESTAMP,HOSTNAME,APPNAME,PRIORITY","1");`  
 
  Input: (String commaSeparatedHeader,String sessionId) 
  
  Initializes table header and session id (should be unique).

- `downSpiralLogs("ids,28,app,something,high");`
   
   Input: (String commaSeparatedLogs) 
  
   Initializes table row data.


- `visualizeLogs();`   

   Input: (void) 
  
   Converts data into JSON and sends it to the server.
  
#### Graphs visualization
  
  
  - `initializeGraph(Types.bar,"1","Growth-over-years");`
   
    Input: (enum Types,String sessionId, String graphHeader) 
    
    `enum Types {
         bar,
         line,
         radar,
         doughnut,
         polarArea,
         bubble,
         scatter
     }`
    
    Initializes graph types,the label for it, and session id (should be unique).
  
  - `downSpiralGraphs("2015-02,2015-03,2015-04","50,45,55");`
     
     Input: (String commaSeparatedDataLabels,commaSeparatedData) 
    
     Initializes graph data with its labels.
  
  
  - `visualizeGraphs();`   
  
     Input: (void) 
    
     Converts data into JSON and sends it to the server.