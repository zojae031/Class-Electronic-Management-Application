package pc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PcServer extends Thread {
    private static PcServer pcServer;
    private ServerSocket pcServerSocket;
    private Socket socket;
    private ArrayList<PcSocket> pcs;

    private boolean statePC[][][] = {
            {{false},{false}},
            {{false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false}}};

    synchronized public static PcServer getInstance(){
        if(pcServer == null) pcServer = new PcServer();
        return pcServer;
    }

    public PcServer(){
        pcs = new ArrayList<PcSocket>();
    }
    @Override
    public void run(){
        try{
            System.out.println("pc server Start");
            pcServerSocket = new ServerSocket(3000);
            while(true){
                socket = pcServerSocket.accept();
                System.out.println(socket.getInetAddress().toString());
                PcSocket pc = new PcSocket(socket, pcPosCheck(socket.getInetAddress().toString()));
                pcs.add(pc);
                pc.start();
            }
        }catch (Exception e){
            System.out.println("PcServer ERROR");
        }
    }

    private String pcPosCheck(String ip){
        ip = ip.substring(1, ip.length());
        for(int k = 0; k < 2; k++) {
            for (int i = 0; i < PcConstants.PCIP[k].length; i++){
                for (int j = 0; j < PcConstants.PCIP[k][i].length; j++) {
                    if (ip.equals(PcConstants.PCIP[k][i][j])) {
                        statePC[k][i][j] = true;
                        return PcConstants.ROOM[k] + ((i * PcConstants.PCIP[k][i].length) + j + 1);
                    }
                }
            }
        }
        return null;
    }

    public void setStatePC(int room, int y, int x) {
        this.statePC[room][y][x] = false;
    }

    public void sendMsg(String pc){
        for (PcSocket socketPc : pcs) {
            if(socketPc.checkId(pc)) {
                socketPc.sendClose();
                break;
            }
        }
    }
    public void sendMsgAll(){
        for (PcSocket socketPc : pcs) {
            socketPc.sendClose();
        }
    }
    public JsonArray getState(int room) {
        JsonArray arr = new JsonArray();

        for(int i = 0; i < statePC[room].length; i++){

            for(int j = 0; j < statePC[room][i].length; j++){
                JsonObject data = new JsonObject();
                data.addProperty( "ip",PcConstants.PCIP[room][i][j]);
                data.addProperty( "flag",statePC[room][i][j]);
                arr.add(data);
            }
        }
        return arr;
    }

}