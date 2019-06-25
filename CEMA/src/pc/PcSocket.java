package pc;

import app.AppServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PcSocket extends Thread {

    private PrintWriter outMsg;
    private BufferedReader inMsg;
    private String pc_id;
    private int room;
    public PcSocket(Socket socket, String id){
        try {
            pc_id = id;
            room = pc_id.substring(0,4).equals(PcConstants.ROOM[0]) ? 0 : 1;
            this.inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outMsg = new PrintWriter(socket.getOutputStream(), true);
            AppServer.getInstance().send();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            inMsg.readLine();
        }catch (Exception e){
            setPcFlag(false);
            System.out.println("PcSocket ERROR");
        }
    }

    private void setPcFlag(boolean flag){
        int number = Integer.valueOf(pc_id.substring(4,pc_id.length())) - 1;
        int y = number / PcConstants.PCIP[room][0].length;
        int x = number - y * PcConstants.PCIP[room][0].length;

        PcServer.getInstance().setStatePC(room, y, x);
        if(flag) sendClose();
    }
    public boolean checkId(String pc){
        return pc_id.equals(pc);
    }
    public void sendClose(){
        try{
            outMsg.println("close");
            setPcFlag(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
