package com.esiea.tp4A;

public interface Position {
    int getX();
    int getY();
    Direction getDirection();
    static Position of(int x, int y, Direction direction) {
        return new FixedPosition(x, y, direction);
    }
    final class FixedPosition implements Position {
        private final int x;
        private final int y;
        private final Direction direction;
        public FixedPosition(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public Direction getDirection() {
            return direction;
        }
    }
}