package main;

import app.AppServer;

public class ServerMain {
    public static void main(String[] args) {
        AppServer appServer = AppServer.getInstance();
        appServer.start();

    }

}
