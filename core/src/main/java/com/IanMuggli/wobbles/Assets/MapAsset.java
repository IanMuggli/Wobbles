package com.IanMuggli.wobbles.Assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public enum MapAsset implements Asset<TiledMap> {
    TestMap("TestMap.tmx");

    private final AssetDescriptor<TiledMap> descriptor;
    TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
    parameters.projectFilePath = "Images/Maps/WobblesSampleTiles.tiled-project";
    MapAsset(String mapName) {
        this.descriptor = new AssetDescriptor<TiledMap>("Images/Maps/" + mapName, TiledMap.class, parameters);
    }

    @Override
    public AssetDescriptor getDescriptor() {
        return descriptor;
    }
}
