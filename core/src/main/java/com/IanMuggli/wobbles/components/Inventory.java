package com.IanMuggli.wobbles.components;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends GameObject {
    int slotCount;
    List<Item> items;
    float xPos; //Player x position in game units
    float yPos; //Player y position in game units
    boolean isOpen;
    float[] color;

    public Inventory(float xPos, float yPos) {
        this.slotCount = 8;
        this.items = new ArrayList<Item>();
        this.xPos = xPos;
        this.yPos = yPos;
        this.isOpen = false;
        this.color = new float[]{0.2f,0.1f,0.39f, 1f};
    }

    //Set Methods
    public void setXPos(float xPos) {this.xPos = xPos;}
    public void setYPos(float yPos) {this.yPos = yPos;}

    //Get Methods
    @Override
    public float getXPos() {return this.xPos;}
    @Override
    public float getYPos() {return this.yPos;}
    @Override
    public float[] getColor() {return this.color;}
}
