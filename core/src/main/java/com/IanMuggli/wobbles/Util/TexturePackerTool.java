package com.IanMuggli.wobbles.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {

    public static void main(String[] args)
    {
//        String inputDir = "C:\\Users\\ianmu\\OneDrive\\Desktop\\Wobbles\\core\\src\\main\\java\\com\\IanMuggli\\wobbles\\Assets\\Images\\PlayerWalkAnimation";
//        String outDir = "C:\\Users\\ianmu\\OneDrive\\Desktop\\Wobbles\\core\\src\\main\\java\\com\\IanMuggli\\wobbles\\Assets\\Images\\PlayerWalkAnimation";
//        String packFileName = "WalkAnimation";
//
//        TexturePacker.process(inputDir,outDir,packFileName);
        AssetManager manager = new AssetManager();
        manager.load("Images/WobblesTestBackground.png", Texture.class);
        //System.out.println(Gdx.files.getExternalStoragePath());
        //System.out.println(Gdx.files.getLocalStoragePath());
        //FileHandle[] files = Gdx.files.absolute("C:\\Users\\ianmu\\Desktop\\Wobbles\\assets").list();
        int x = 1;
    }
}
