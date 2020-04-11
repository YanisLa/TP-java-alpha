package com.esiea.tp4A.test;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;
import com.esiea.tp4A.domain.Rover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    @Test
    void maxNSMove () {
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(0,50,Direction.NORTH)).updateMap(()->new HashSet<>()).move("f").getY(),-49);
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(0,-49,Direction.SOUTH)).updateMap(()->new HashSet<>()).move("f").getY(),50);
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(0,50,Direction.SOUTH)).updateMap(()->new HashSet<>()).move("b").getY(),-49);
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(0,-49,Direction.NORTH)).updateMap(()->new HashSet<>()).move("b").getY(),50);
    }
    @Test
    void maxEWMove () {
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(50,0,Direction.EAST)).updateMap(()->new HashSet<>()).move("f").getX(),-49);
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(-49,0,Direction.WEST)).updateMap(()->new HashSet<>()).move("f").getX(),50);
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(-49,0,Direction.EAST)).updateMap(()->new HashSet<>()).move("b").getX(),50);
        Assertions.assertEquals(new Rover().initialize(new Position.FixedPosition(50,0,Direction.WEST)).updateMap(()->new HashSet<>()).move("b").getX(),-49);
    }
    @Test
    void normalMove () {
        MarsRover rover = Rover.newRover();
        Assertions.assertEquals(rover.updateMap(()-> new HashSet<>()).move("ffff").getY(),4);
        Assertions.assertEquals(rover.updateMap(()-> new HashSet<>()).move("bbbb").getY(),0);
        Position position = rover.updateMap(()-> new HashSet<>()).move("fllfrr");
        Assertions.assertEquals(position.getY(),0);
        Assertions.assertEquals(position.getX(),0);
        Assertions.assertEquals(position.getDirection(),Direction.NORTH);
    }
    @Test
    void obstacleMove () {
        MarsRover rover = Rover.newRover().updateMap(()->new HashSet<>(){{
            add(new Position.FixedPosition(0,1,Direction.NORTH));
        }});
        Position position = rover.move("f");
        Assertions.assertEquals(position.getY(),0);
        Assertions.assertEquals(position.getX(),0);
        Assertions.assertEquals(position.getDirection(),Direction.NORTH);
    }
    @Test
    void fire () {
        MarsRover rover = new Rover()
                .updateMap(()-> new HashSet<>(){{
                    new Position.FixedPosition(0,1,Direction.NORTH);
                }})
                .initialize(new Position.FixedPosition(0,0,Direction.NORTH))
                .configureLaserRange(1);
        Position position = rover.move("sf");
        Assertions.assertEquals(position.getY(),1);
        Assertions.assertEquals(position.getX(),0);
        Assertions.assertEquals(position.getDirection(),Direction.NORTH);
    }
}