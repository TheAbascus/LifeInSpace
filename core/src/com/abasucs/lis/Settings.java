package com.abasucs.lis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Abascus on 26.12.2015.
 */
public class Settings
{
    public static Preferences prefData;
    public static boolean showFPS;

    public static void setup()
    {
        prefData = Gdx.app.getPreferences("com.abasucs.lis.Settings");
        if(!prefData.contains("vSync"))
        {
            prefData.putBoolean("vSync", true);
        }
        if(!prefData.contains("width"))
        {
            prefData.putInteger("width", (int)(Gdx.graphics.getDesktopDisplayMode().width/1.5));
        }
        if(!prefData.contains("height"))
        {
            prefData.putInteger("height", (int)(Gdx.graphics.getDesktopDisplayMode().height/1.5));
        }
        if(!prefData.contains("fullscreen"))
        {
            prefData.putBoolean("fullscreen", false);
        }
        if(!prefData.contains("showFPS"))
        {
            prefData.putBoolean("showFPS", false);
        }
        prefData.flush();

        Gdx.graphics.setVSync(prefData.getBoolean("vSync"));

        Gdx.graphics.setDisplayMode(prefData.getInteger("width"), prefData.getInteger("height"), prefData.getBoolean("fullscreen"));
    }

    public static void setShowFPS(boolean fps)
    {
        showFPS = fps;
        prefData.putBoolean("showFPS", fps);
        prefData.flush();
    }

    public static void setVSync(boolean vSync)
    {
        Gdx.graphics.setVSync(vSync);
        prefData.putBoolean("vSync", vSync);
        prefData.flush();
    }

    public static void setFullscreen(boolean fullscreen)
    {
        Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, fullscreen);
        prefData.putBoolean("fullscreen", fullscreen);
        prefData.flush();
    }

    public static void setScreenSize(int x, int y)
    {
        Gdx.graphics.setDisplayMode(x, y, Gdx.graphics.isFullscreen());
        prefData.putInteger("width", x);
        prefData.putInteger("height", y);
        prefData.flush();
    }
}
