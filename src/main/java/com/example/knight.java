package com.example;

public class knight {
    int x;
    int y;
    String dir;

    public knight(){
        this.x = 0;
        this.y = 0;
        this.dir = "NORTH";
    }  

    public void start(int _x, int _y, String _dir){
        this.x = _x;
        this.y = _y;
        this.dir = _dir;
    }

    public void move(){
        if(this.dir.equals("NORTH")){
            this.y += 1;
        }
        if(this.dir.equals("SOUTH")){
            this.y -= 1; 
        }
        if(this.dir.equals("EAST")){
            this.x += 1;
        }
        if(this.dir.equals("WEST")){
            this.x -= 1;
        }
    }

    public void reverse(){
        if(this.dir.equals("NORTH")){
            this.y -= 1;
        }
        if(this.dir.equals("SOUTH")){
            this.y += 1; 
        }
        if(this.dir.equals("EAST")){
            this.x -= 1;
        }
        if(this.dir.equals("WEST")){
            this.x += 1;
        }
    }

    public void rotate(String _dir){
        this.dir = _dir;
    }

    public int getx(){
        return this.x;
    }

    public int gety(){
        return this.y;
    }

    public String getdir(){
        return this.dir;
    }

}
