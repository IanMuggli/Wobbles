package com.IanMuggli.wobbles.components;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.List;

public class Player extends GameObject {
    float height; //Player hitbox height in game units
    float width; //Player hitbox width in game units
    float xPos; //Player x position in game units
    float yPos; //Player y position in game units
    float xVelocity; //Player velocity in game units per second
    float yVelocity; //Player velocity in game units per second
    float[] color;
    Inventory inventory;
    OrthographicCamera playerCamera;

    public Player() {
        this.height = 80;
        this.width = 24;
        this.xPos = 0;
        this.yPos = 0;
        this.color = new float[]{0.04f, 0.17f, 0.13f, 1f};
        this.inventory = new Inventory(this.xPos,this.yPos);
        this.playerCamera = new OrthographicCamera();
    }

    public void move(List<Integer> inputList)
    {
        this.calcVelocity(inputList);
        this.updatePosition();
        this.updateInventoryPosition();
        this.updateCameraPosition();
    }

    private void calcVelocity (List<Integer> inputList)
    {
        if (inputList.contains(Input.Keys.A))
        {
            if(this.xVelocity > 0f) {this.xVelocity = 0f;} //Reset velocity to 0 if direction changed
            this.xVelocity += -0.2f;
            if(this.xVelocity < -10f) {this.xVelocity = -10f;} //Cap speed at 10
        }

        if (inputList.contains(Input.Keys.D))
        {
            if(this.xVelocity < 0f) {this.xVelocity = 0f;} //Reset velocity to 0 if direction changed
            this.xVelocity += 0.2f;
            if(this.xVelocity > 10f) {this.xVelocity = 10f;} //Cap speed at 10
        }

        //If both A+D are pressed, or no buttons are pressed, decelerate
        if ((inputList.contains(Input.Keys.D) && inputList.contains(Input.Keys.A))
            || inputList.isEmpty())
        {
            if(this.xVelocity < 0f)
            {
                this.xVelocity += 0.3f;
                if(this.xVelocity > 0) {this.xVelocity = 0;} //Prevent overshooting 0
            }

            if(this.xVelocity > 0f)
            {
                this.xVelocity -= 0.3f;
                if(this.xVelocity < 0) {this.xVelocity = 0;} //Prevent overshooting 0
            }
        }
    }

    private void updatePosition ()
    {
        this.xPos += this.xVelocity;
        this.yPos += this.yVelocity;
    }

    private void updateCameraPosition ()
    {
        this.playerCamera.position.set(this.xPos,this.yPos,0);
    }

    private void updateInventoryPosition ()
    {
        //If the inventory is within 80 units of the player's position, don't move it.
        if (Math.abs(this.inventory.getXPos() - this.xPos) < 80)
        {
            return;
        }

        this.inventory.xPos += this.xVelocity;
        this.inventory.yPos += this.yVelocity;
    }

    //Get Methods
    public Inventory getInventory() {return this.inventory;}
    public OrthographicCamera getCamera() {return this.playerCamera;}

    @Override
    public float getXPos() {return this.xPos;}
    @Override
    public float getYPos() {return this.yPos;}
}
