package com.IanMuggli.wobbles.components;

import com.IanMuggli.wobbles.Assets.Asset;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public enum AtlasAsset implements Asset<TextureAtlas> {
    OBJECTS("objects.atlas");

    private final AssetDescriptor<TextureAtlas> descriptor;

    AtlasAsset(String atlasName) {
        this.descriptor = new AssetDescriptor<>("Images/" + atlasName, TextureAtlas.class);
    }

    @Override
    public AssetDescriptor<TextureAtlas> getDescriptor() {
        return descriptor;
    }
}
