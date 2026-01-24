package com.IanMuggli.wobbles;

import com.IanMuggli.wobbles.Assets.MapAsset;
import com.IanMuggli.wobbles.components.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    private final Main game;
    private final Texture mapTexture;
    private OrthoCachedTiledMapRenderer mapRenderer;
    private TiledMap map;

    FirstScreen(Main game) {
        this.game = game;
        this.mapTexture = new Texture("Images/WobblesTestBackground.png");
    }

    @Override
    public void show() {
        this.map = this.game.wobblesAssetService.load(MapAsset.TestMap);
        this.mapRenderer = new OrthoCachedTiledMapRenderer(map, 1f);
    }

    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set a black color
        this.game.spriteBatch.setColor(Color.WHITE);

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

        this.mapRenderer.setView(this.game.player.getCamera());
        this.mapRenderer.render();

        game.spriteBatch.begin();
        for(GameObject gameObject:game.renderedGameObjects)
        {
            game.spriteBatch.draw(new Texture("Images/PlayerIdleAnimation/WobbleCharecter_idle_01.png"), gameObject.getXPos(),gameObject.getYPos(),39,54);
        }
        game.spriteBatch.end();
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
        this.map.dispose();
        this.game.spriteBatch.dispose();
        this.mapRenderer.dispose();
    }
}
