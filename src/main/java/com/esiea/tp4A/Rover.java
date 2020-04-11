package com.esiea.tp4A;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Rover implements MarsRover {
    private int laserRange;
    private PlanetMap map;
    private Position position;
    private static Set<Position> obstacle = null;
    public static final int laserRanger = 3;
    public static MarsRover newRover () {
        if (Rover.obstacle == null) Rover.obstacle = setObstacle();
        return new Rover().initialize(new Position.FixedPosition((Math.abs(new Random().nextInt())%100)-49,(Math.abs(new Random().nextInt())%100)-49,Direction.NORTH))
                .configureLaserRange(Rover.laserRanger)
                .updateMap(() -> Rover.obstacle);
    }
    public static Set<Position> setObstacle() {
        return new HashSet<>(){{
            Random random = new Random();
            for(int i=0;i<150;i++) {
                Position position = new Position.FixedPosition((Math.abs(random.nextInt())%100)-49,(Math.abs(random.nextInt())%100)-49,Direction.NORTH);
                if (!contains(position)) add(position);
            }
        }};
    }
    public MarsRover configureLaserRange (int range) {
        this.laserRange = range;
        return this;
    }
    public MarsRover updateMap (PlanetMap map) {
        this.map = map;
        return this;
    }
    public MarsRover initialize(Position position) {
        this.position = position;
        return this;
    }
    public Position move(String commade) {
        for(char com : commade.toCharArray()) {
            if (com == 's') this.fire(laserRange,this.position);
            else this.position = Move.move(this.position,com,this.map);
        }
        return this.position;
    }
    private void fire(int laserRange,Position position) {
        if(laserRange == 0) return;
        for(Position obstacle : this.map.obstaclePositions()) {
            if (obstacle.getX() == position.getX() && obstacle.getY() == position.getY()) {
                this.map.obstaclePositions().remove(obstacle);
                return;
            }
        }
        fire(laserRange-1,Move.move(position,'s',this.map));
    }
}
