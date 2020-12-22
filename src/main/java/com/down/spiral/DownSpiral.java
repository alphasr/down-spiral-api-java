
public class DownSpiral {
        private  ITableLog payloadLogs;
        private Graph payloadGraph;
        void initializeLogs(String header[],String sessionId) throws InterruptedException {
            ITableLog payloadLogs = new ITableLog(header, "1");
            this.payloadLogs = payloadLogs;
            visualize();
        }
        void downSpiralLogs(String data[]){
            payloadLogs.addLogs(data);

        }

        void visualize() throws InterruptedException {
        Visualize obj = new Visualize();
        obj.setLogs(payloadLogs, true);
        String tableData = obj.getTableData();
        obj.sendData(tableData);
        }


    void downSpiralGraphs(){

    }

}
