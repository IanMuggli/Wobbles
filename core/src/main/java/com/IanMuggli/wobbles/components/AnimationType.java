package com.IanMuggli.wobbles.components;

import com.badlogic.ashley.core.Component;

public enum AnimationType implements Component {
    Walk,Idle;

    private final String atlasKey;

    AnimationType(){
        this.atlasKey = name().toLowerCase();
    }

    public String getAtlasKey(){
        return this.atlasKey;
    }
}
