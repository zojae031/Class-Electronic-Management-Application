package main;

import app.AppServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ServerMain {
    public static void main(String[] args) {
        AppServer appServer = new AppServer();
        appServer.start();

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(100,100));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TextField textField = new TextField();
        JButton btn = new JButton("close");
        textField.setBounds(0,0,100,100);
        btn.setBounds(100,1000,100,100);
        btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // appServer.close(textField.getText().toString());
            }
        });
        frame.getContentPane().add(textField);
        frame.getContentPane().add(btn);

        frame.pack();
    }

}
