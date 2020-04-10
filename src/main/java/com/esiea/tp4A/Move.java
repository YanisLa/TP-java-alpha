package com.esiea.tp4A;

import java.util.Set;

public class Move {
    public static Position move (Position position, char commande, PlanetMap map) {
        if (commande == 'l') return leftMove(position);
        if (commande == 'r') return rightMove(position);
        if (commande == 'f') return upMove(position, map, false);
        if (commande == 'b') return backMove(position, map);
        if (commande == 's') return upMove(position, map, true);
        return position;
    }
    private static Position backMove (Position position, PlanetMap map) {
        int x = position.getX(),y = position.getY();
        if (position.getDirection() == Direction.NORTH) y--;
        if (position.getDirection() == Direction.WEST) x++;
        if (position.getDirection() == Direction.SOUTH) y++;
        if (position.getDirection() == Direction.EAST) x--;
        return moveable(adjust(x),adjust(y),map) ? new Position.FixedPosition(adjust(x),adjust(y),position.getDirection()) : position;
    }
    public static Position upMove (Position position, PlanetMap map, boolean isLaser) {
        int x = position.getX(),y = position.getY();
        if (position.getDirection() == Direction.NORTH) y++;
        if (position.getDirection() == Direction.WEST) x--;
        if (position.getDirection() == Direction.SOUTH) y--;
        if (position.getDirection() == Direction.EAST) x++;
        x = adjust(x);
        y = adjust(y);
        return isLaser ? new Position.FixedPosition(x,y,position.getDirection()) :  (moveable(x,y,map) ? new Position.FixedPosition(x,y,position.getDirection()) : position);
    }
    private static int adjust (int value) {
        if (value == -50) value =  50;
        if (value ==  51) value = -49;
        return value;
    }

    private static Position rightMove (Position position) {
        if (position.getDirection() == Direction.NORTH) return new Position.FixedPosition(position.getX(),position.getY(),Direction.EAST);
        if (position.getDirection() == Direction.WEST) return new Position.FixedPosition(position.getX(),position.getY(),Direction.NORTH);
        if (position.getDirection() == Direction.SOUTH) return new Position.FixedPosition(position.getX(),position.getY(),Direction.WEST);
        if (position.getDirection() == Direction.EAST) return new Position.FixedPosition(position.getX(),position.getY(),Direction.SOUTH);
        return position;
    }
    private static Position leftMove (Position position) {
        if (position.getDirection() == Direction.NORTH) return new Position.FixedPosition(position.getX(),position.getY(),Direction.WEST);
        if (position.getDirection() == Direction.WEST) return new Position.FixedPosition(position.getX(),position.getY(),Direction.SOUTH);
        if (position.getDirection() == Direction.SOUTH) return new Position.FixedPosition(position.getX(),position.getY(),Direction.EAST);
        if (position.getDirection() == Direction.EAST) return new Position.FixedPosition(position.getX(),position.getY(),Direction.NORTH);
        return position;
    }
    private static boolean moveable (int x,int y, PlanetMap map) {
        for(Position position : map.obstaclePositions()) {
            if (position.getX() == x && position.getY() == y) {
                return false;
            }
        }
        return true;
    }
}
