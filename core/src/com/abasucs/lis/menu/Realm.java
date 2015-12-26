package com.abasucs.lis.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abascus on 26.12.2015.
 */
public class Realm
{
    String name;
    Label label;
    Map<String, TextButton> levels = new HashMap<String,TextButton>();

    public Realm(String n, Label l)
    {
        name = n;
        label = l;
    }
}