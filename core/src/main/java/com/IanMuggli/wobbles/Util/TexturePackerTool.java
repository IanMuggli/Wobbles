package com.IanMuggli.wobbles.Util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {

    public static void main(String[] args)
    {
        String inputDir = "C:\\Users\\ianmu\\OneDrive\\Desktop\\Wobbles\\core\\src\\main\\java\\com\\IanMuggli\\wobbles\\Assets\\Images\\PlayerWalkAnimation";
        String outDir = "C:\\Users\\ianmu\\OneDrive\\Desktop\\Wobbles\\core\\src\\main\\java\\com\\IanMuggli\\wobbles\\Assets\\Images\\PlayerWalkAnimation";
        String packFileName = "WalkAnimation";

        TexturePacker.process(inputDir,outDir,packFileName);
    }
}
