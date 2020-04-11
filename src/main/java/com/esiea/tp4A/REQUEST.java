package com.esiea.tp4A;

import java.io.PrintWriter;
import java.util.*;

public class REQUEST {
    private static HashMap<String, MarsRover> rovers = new HashMap<>();
    public static void POST(String player, PrintWriter writer) {
        if(rovers.containsKey(player)) ko("409 Conflict", writer);
        else {
            rovers.put(player, Rover.newRover());
            ok(player,writer);
        }
    }
    public static void GET(String player, PrintWriter writer) {
        MarsRover rover = rovers.get(player);
        if (rover == null) ko("404 Not Found", writer);
        else ok(player, writer);
    }
    public static void PATCH(String player, String commande, PrintWriter writer) {
        MarsRover rover = rovers.get(player);
        if (rover == null) ko("404 Not Found", writer);
        else {
            rovers.get(player).move("f");
            ok(player, writer);
        }
    }
    private static void ko(String code, PrintWriter writer) {
        send("HTTP/1.0 "+code+"\r\n\r\n", writer);
    }
    private static void send (String message, PrintWriter writer) {
        writer.print(message);
        writer.flush();
    }
    private static void ok(String player, PrintWriter writer) {
        String message = "HTTP/1.0 200 OK\r\nContent-Type: application/json\r\n\r\n";
        message += "{\"player\":{\"name\":\"" + player + "\",\"status\":\"Alive\",\"position\":" + position(player) + ",\"laser-range\":"+Rover.laserRanger+"},\"local-map\":{\"obstacle\":" + obstacle() +",\"players\":" + players(player) + "}}";
        send(message,writer);
    }

    private static String players(String player) {
        HashMap<String, MarsRover> rovers = new HashMap<>(){{putAll(REQUEST.rovers);remove(player);}};
        String message = "";
        int i = 0, max = rovers.size() - 1;
        for (Map.Entry<String, MarsRover> entry : rovers.entrySet()) {
            message += "{\"player\":{\"name\":\"" + entry.getKey() + "\",\"status\":\"Alive\",\"position\":" + position(entry.getValue().move("")) + ",\"laser-range\":"+Rover.laserRanger+"}}";
            if(i != max) message += ",";
            i++;
        }
        return "[" + message + "]";
    }

    private static String obstacle() {
        String message = "";
        int i = 0, max = Rover.setObstacle().size() - 1;
        for (Position position : Rover.setObstacle()) {
            message += position(position);
            if(i != max) message += ",";
            i++;
        }
        return "[" + message + "]";
    }
    private static String position (String player) {
        return position(rovers.get(player).move(""));
    }
    private static String position (Position position) {
        return "{\"x\":"+position.getX()+",\"y\":"+position.getY()+",\"direction\":\""+position.getDirection()+"\"}";
    }
}
