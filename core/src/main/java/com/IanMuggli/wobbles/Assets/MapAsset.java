package com.IanMuggli.wobbles.Assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapAsset implements Asset<TiledMap> {

    private final AssetDescriptor<TiledMap> descriptor;

    public MapAsset(String mapName) {
        this.descriptor = new AssetDescriptor<TiledMap>("maps/" + mapName, TiledMap.class);
    }

    @Override
    public AssetDescriptor getDescriptor() {
        return null;
    }
}
