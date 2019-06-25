package app;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppSocket extends Thread {

    private BufferedReader inMsg;
    private PrintWriter outMsg;
    private JsonParser parser;
    private JsonObject object;
    private String msg;
    private Reaction reaction;

    public AppSocket(Socket socket){
        try{
            this.inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outMsg = new PrintWriter(socket.getOutputStream(), true);
            this.parser = new JsonParser();
            reaction = new Reaction();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try{
            while(true){
                msg = inMsg.readLine();
                System.out.println(msg);
                if(msg == null) break;
                object = (JsonObject) parser.parse(msg);
                outMsg.println(reaction.reaction(object).toString());
            }
        }catch (Exception e){
            System.out.println("AppSocket ERROR");
        }
    }
}
