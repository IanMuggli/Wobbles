package com.IanMuggli.wobbles.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation2D implements Component {
    public static final ComponentMapper<Animation2D> MAPPER = ComponentMapper.getFor(Animation2D.class);

    private AtlasAsset atlasAsset;
    private String atlasKey;
    private AnimationType animationType;
    private Facing.FacingDirection facing;
    private Animation.PlayMode playMode;
    private float speed;
    private float stateTime;
    private Animation<TextureRegion> animation;
    private boolean isDirty;

    public Animation2D(AtlasAsset atlasAsset,
                       String atlasKey,
                       AnimationType animationType,
                       Facing.FacingDirection facing,
                       Animation.PlayMode playMode,
                       float speed) {
        this.atlasAsset = atlasAsset;
        this.atlasKey = atlasKey;
        this.animationType = animationType;
        this.facing = facing;
        this.playMode = playMode;
        this.speed = speed;
        this.stateTime = 0f;
        this.animation = null;
    }

    public void setAnimation(Animation<TextureRegion> animation, Facing.FacingDirection facing) {
        this.facing = facing;
        this.animation = animation;
        this.stateTime = 0f;
        this.isDirty = false;
    }

    //Getters
    public Facing.FacingDirection getFacing() {return facing;}
    public AtlasAsset getAtlasAsset() {return atlasAsset;}
    public String getAtlasKey() {return atlasKey;}
    public AnimationType getAnimationType() {return animationType;}
    public Animation.PlayMode getPlayMode() {return playMode;}
    public float getSpeed() {return speed;}
    public float getStateTime() {return stateTime;}
    public Animation<TextureRegion> getAnimation() {return animation;}
    //Setters
    public void setSpeed(float speed) {this.speed = speed;}
    public void setPlayMode(Animation.PlayMode playMode) {this.playMode = playMode;}

    public boolean isDirty() {return isDirty;}

    public float updateStateTime(float deltaTime){
        this.stateTime = deltaTime * this.speed;
        return this.stateTime;
    }
}
