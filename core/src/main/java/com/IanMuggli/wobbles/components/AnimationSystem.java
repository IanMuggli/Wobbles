package com.IanMuggli.wobbles.components;

import com.IanMuggli.wobbles.Assets.WobblesAssetService;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class AnimationSystem extends IteratingSystem {

    private final WobblesAssetService wobblesAssetService;
    private final Map<CacheKey, Animation<TextureRegion>> animationCache;

    public AnimationSystem (WobblesAssetService wobblesAssetService){
        super(Family.all(Animation2D.class, Graphic.class, Facing.class).get());
        this.wobblesAssetService = wobblesAssetService;
        this.animationCache = new HashMap<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime){
        Animation2D animation2D = Animation2D.MAPPER.get(entity);
        Facing.FacingDirection facing = Facing.MAPPER.get(entity).getDirection();
        final float stateTime;
        if(animation2D.isDirty() || facing != animation2D.getFacing())
        {
            updateAnimation(animation2D, facing);
            stateTime = 0f;
        }
        else
        {
            stateTime = animation2D.updateStateTime(deltaTime);
        }

        Animation<TextureRegion> animation = animation2D.getAnimation();
        animation.setPlayMode(animation2D.getPlayMode());
        TextureRegion keyFrame = animation.getKeyFrame(stateTime);
        Graphic.MAPPER.get(entity).setRegion(keyFrame);
    }

    private void updateAnimation(Animation2D animation2D, Facing.FacingDirection facingDirection)
    {
        AtlasAsset atlasAsset = animation2D.getAtlasAsset();
        String atlasKey = animation2D.getAtlasKey();
        AnimationType animationType = animation2D.getAnimationType();
        CacheKey cacheKey = new CacheKey(atlasAsset, atlasKey, animationType, facingDirection);
        Animation<TextureRegion> animation = this.animationCache.computeIfAbsent(cacheKey, key -> {
            TextureAtlas textureAtlas = this.wobblesAssetService.get(atlasAsset);
            String combinedKey = atlasKey;
            Array<TextureAtlas.AtlasRegion> regions = textureAtlas.findRegions(atlasKey);
            if (regions.isEmpty()) {throw new GdxRuntimeException("No regions found for key: " + combinedKey);}

            return new Animation<>(1f/8f, regions);
        });
        animation2D.setAnimation(animation,facingDirection);
    }

    public record CacheKey(
        AtlasAsset atlasAsset,
        String atlasKey,
        AnimationType type,
        Facing.FacingDirection facing
    ) {
    }
}
