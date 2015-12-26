package com.abasucs.lis;

import com.abasucs.lis.menu.MainMenuScreen;
import com.abasucs.lis.menu.UIHelper;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Main extends Game
{
    public static int PLATFORM;
    public IActivityRequestHandler reqH;

    public static final int PLATFORM_DESKTOP = 0;
    public static final int PLATFORM_ANDROID = 1;
    public static final int PLATFORM_HTML = 2;
    public static final int PLATFORM_IOS = 3;

    public Main(IActivityRequestHandler reqH, int p)
    {
        this.reqH = reqH;
        PLATFORM = p;
    }

    @Override
    public void create()
    {
        UIHelper.setup();

        Settings.setup();

        setScreen(new MainMenuScreen(this));

    }
}
