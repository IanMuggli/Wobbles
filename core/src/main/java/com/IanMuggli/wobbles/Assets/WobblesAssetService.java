package com.IanMuggli.wobbles.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;

public class WobblesAssetService implements Disposable {
    private final AssetManager assetManager;

    public WobblesAssetService(FileHandleResolver fileHandleResolver) {
        this.assetManager = new AssetManager(fileHandleResolver);
        this.assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    }

    public <T> T load(Asset<T> asset)
    {
        assetManager.load(asset.getDescriptor());
        this.assetManager.finishLoading();
        return this.assetManager.get(asset.getDescriptor());
    }

    public <T> void queue(Asset<T> asset)
    {
        assetManager.load(asset.getDescriptor());
    }

    public <T> T get(Asset<T> asset)
    {
        return this.assetManager.get(asset.getDescriptor());
    }

    public <T> boolean update(int millisecondsToBlock)
    {
        return this.assetManager.update(millisecondsToBlock);
    }

    public <T> void debugDiagnostics(Asset<T> asset)
    {
        Gdx.app.debug("WobblesAssetService", this.assetManager.getDiagnostics());
    }

    @Override
    public void dispose() {
        this.assetManager.dispose();
    }
}
