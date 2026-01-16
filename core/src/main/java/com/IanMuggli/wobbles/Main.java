package com.IanMuggli.wobbles;

import com.IanMuggli.wobbles.Util.ScrollInput;
import com.IanMuggli.wobbles.components.GameObject;
import com.IanMuggli.wobbles.components.Player;
import com.IanMuggli.wobbles.controlStateMachine.ControlActions;
import com.IanMuggli.wobbles.controlStateMachine.ControlContext;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    ShapeRenderer shapeRenderer;
    Viewport viewport;
    List<Integer> inputList;
    ControlContext controlContext;
    InputProcessor inputProcessor;

    //Game Objects
    List<GameObject> renderedGameObjects;
    Player player;

    @Override
    public void create() {
        //Create Game Objects
        this.renderedGameObjects = new ArrayList<>();
        this.player = new Player();

        //Create Infrastructure
        this.shapeRenderer = new ShapeRenderer();
        this.viewport = new ScreenViewport(this.player.getCamera());
        this.inputList = new ArrayList<>();
        this.controlContext = new ControlContext();
        this.inputProcessor = new ScrollInput(this.player.getCamera());
        Gdx.input.setInputProcessor(this.inputProcessor);

        //Add player to the list of rendered objects
        this.renderedGameObjects.add(this.player);

        setScreen(new FirstScreen(this));
    }

    @Override
    public void render() {
        this.input();
        this.logic();
        this.draw();
    }

    private void input() {
        this.inputList.clear(); //Reset captured inputs
        if (Gdx.input.isKeyPressed(Input.Keys.A)){inputList.add(Input.Keys.A);}
        if (Gdx.input.isKeyPressed(Input.Keys.D)){inputList.add(Input.Keys.D);}
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){inputList.add(Input.Keys.E);}
    }

    private void logic() {
        switch (this.controlContext.getState())
        {
            case InventoryOpen:
                if(this.inputList.contains(Input.Keys.E))
                {
                    this.renderedGameObjects.remove(this.player.getInventory());
                    //close the player's inventory
                    this.controlContext.doAction(ControlActions.CloseInventory);
                }
                break;
            case InventoryClosed:
                if(this.inputList.contains(Input.Keys.E))
                {
                    //open the player's inventory
                    this.player.getInventory().setXPos(this.player.getXPos()-35f);
                    this.player.getInventory().setYPos(this.player.getYPos()+20f);
                    this.renderedGameObjects.add(this.player.getInventory());
                    this.controlContext.doAction(ControlActions.OpenInventory);
                }
                break;
        }
        this.player.move(this.inputList);
    }

    private void draw() {
        float delta = Gdx.graphics.getDeltaTime();
        screen.render(delta);
    }
}
