package com.mygdx.engine.entity;

public interface IMoveable {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public void moveLeft();

    public void moveRight();

    public void moveUp();

    public void moveDown();
}
