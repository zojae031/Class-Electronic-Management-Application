package app;

import pc.PcServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AppServer extends Thread{
    private ServerSocket appServer;
    private Socket socket;
    private PcServer pcServer;
    public ArrayList<AppSocket> clients;
    public AppServer(){
        pcServer = new PcServer();
        pcServer.start();
        clients = new ArrayList<AppSocket>();
    }
    @Override
    public void run(){
        try{
            System.out.println("app server Start");
            appServer = new ServerSocket(5050);
            while(true){
                socket = appServer.accept();
                AppSocket client = new AppSocket(socket);
                clients.add(client);
                client.start();
            }
        }catch (Exception e){
            System.out.println("AppServer ERROR");
        }
    }
    public void close(String s){
        pcServer.sendMsg(s);
    }
}
