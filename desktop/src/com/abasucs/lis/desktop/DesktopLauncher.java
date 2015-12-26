package com.abasucs.lis.desktop;

import com.abasucs.lis.IActivityRequestHandler;
import com.abasucs.lis.Main;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.Toolkit;

public class DesktopLauncher implements IActivityRequestHandler
{
    private static IActivityRequestHandler handler;
    static Toolkit kit = Toolkit.getDefaultToolkit();

    public static void main(String[] arg)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.resizable = false;
        config.width = (int) (kit.getScreenSize().width / 1.5);
        config.height = (int) (kit.getScreenSize().height / 1.5);
        handler = new DesktopLauncher();
        new LwjglApplication(new Main(handler, Main.PLATFORM_DESKTOP), config);
    }

}
