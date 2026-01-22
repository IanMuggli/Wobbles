package com.IanMuggli.wobbles;

import com.IanMuggli.wobbles.Assets.MapAsset;
import com.IanMuggli.wobbles.components.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    Main game;
    Texture mapTexture;
    private final OrthoCachedTiledMapRenderer mapRenderer;

    FirstScreen(Main game) {
        this.game = game;
        this.mapTexture = new Texture("Images/WobblesTestBackground.png");
        this.mapRenderer = new OrthoCachedTiledMapRenderer(this.game.wobblesAssetService.get(MapAsset.TestMap), 1f/16f);
    }

    @Override
    public void show() {
        this.game.wobblesAssetService.load(MapAsset.TestMap);
    }

    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set a black color
        this.game.spriteBatch.setColor(Color.WHITE);
        this.mapRenderer.setView(this.game.player.getCamera());
        this.mapRenderer.render();

        //Update camera in case zoom changed
        game.player.getCamera().update();

        //Set camera size
        game.spriteBatch.setProjectionMatrix(this.game.player.getCamera().combined);

        //Do Stuff
        game.spriteBatch.begin();
        game.spriteBatch.draw(this.mapTexture,0f,0);
        game.spriteBatch.draw(this.mapTexture,1900f,0);
        game.spriteBatch.draw(this.mapTexture,-1900f,0);
        game.spriteBatch.end();

//        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        for(GameObject gameObject:game.renderedGameObjects)
//        {
//            game.shapeRenderer.setColor(gameObject.getColor()[0],gameObject.getColor()[1],gameObject.getColor()[2],1f);
//            switch (gameObject.getShape())
//            {
//                case Circle:
//                    game.shapeRenderer.circle(gameObject.getXPos(),gameObject.getYPos(),10,10);
//                    break;
//                case Rectangle:
//                    game.shapeRenderer.rect(gameObject.getXPos(),gameObject.getYPos(),40,40);
//                    break;
//            }
//        }
//        game.shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        game.viewport.update(width,height);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        this.game.spriteBatch.dispose();
        this.mapRenderer.dispose();
    }
}
