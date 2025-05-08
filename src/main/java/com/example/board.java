package com.example;

import java.util.List;

public class board {
    int width;
    int height;
    List<obstacle> obstacles;

    public board(int width, int height, List<obstacle> obstacles){
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public boolean isObstacle(int x, int y){
        for(obstacle obs : obstacles){
            if((obs.x == x) && (obs.y == y)){
                return true;
            }
        }
        return false;
    }
}
