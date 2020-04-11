package com.esiea.tp4A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server () {
        new Thread(()->{
            thread();
        }).start();
    }
    private void thread () {
        ServerSocket server;
        if ((server = createServer()) == null) return;
        System.out.println("Serveur lancée");
        while(true) {
            try { request(server.accept()); }
            catch (Exception e) {}
        }
    }
    private void request (Socket client) throws Exception {
        PrintWriter writer = new PrintWriter(client.getOutputStream());
        String receive = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();
        if (receive == null) throw new Exception("Message null");
        String []type = receive.split(" ");
        String []router = type[1].split("/");
        if(!(router[0].equals("") && router[1].equals("api") && router[2].equals("player"))) throw new Exception("Pas de bonne route");
        request(type[0], router, writer, client);
    }
    private void request (String type, String[] router, PrintWriter writer, Socket client) throws Exception {
        if(type.equals("POST") && router.length == 4) REQUEST.POST(router[3], writer);
        if(type.equals("GET")  && router.length == 4) REQUEST.GET(router[3], writer);
        if(type.equals("PATCH")  && router.length == 5) REQUEST.PATCH(router[3], router[4], writer);
        client.close();
    }
    private ServerSocket createServer () {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            return serverSocket;
        } catch (IOException e) {
            return null;
        }
    }
}
