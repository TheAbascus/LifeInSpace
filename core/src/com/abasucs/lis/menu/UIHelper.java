package com.abasucs.lis.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.HashMap;
import java.util.Map;

public class UIHelper
{

    public static TextureAtlas atlas;

    public static Skin uiSkin;

    public static TextButtonStyle TBStyle;
    public static TextButtonStyle invisTBStyle;
    public static TextFieldStyle TFStyle;
    public static CheckBoxStyle CBStyle;
    public static SelectBoxStyle SBStyle;
    public static LabelStyle LStyle;
    public static Map<String, BitmapFont> font = new HashMap<String, BitmapFont>();

    static Texture back_1, back_2, back_3;
    static float timer_1, timer_2, timer_3;
    static float mult = 1.8f;

    public static void setup()
    {
        uiSkin = new Skin(Gdx.files.internal("menu/uiskin.json"));

        TBStyle = uiSkin.get(TextButtonStyle.class);
        TFStyle = uiSkin.get(TextFieldStyle.class);
        CBStyle = uiSkin.get(CheckBoxStyle.class);
        SBStyle = uiSkin.get(SelectBoxStyle.class);
        LStyle = uiSkin.get(LabelStyle.class);

        Drawable invis = new BaseDrawable();
        invisTBStyle = new TextButtonStyle(TBStyle);
        invisTBStyle.up = invis;
        invisTBStyle.down = invis;

        back_1 = new Texture("menu/back_1.png");
        back_2 = new Texture("menu/back_2.png");
        back_3 = new Texture("menu/back_3.png");
    }

    public static void renderStarField(float delta, Batch batch, float Gwidth, float Gheight)
    {
        timer_1 += delta;
        timer_2 += delta;
        timer_3 += delta;

        timer_1 = timer_1 % 16;
        timer_2 = timer_2 % 20;
        timer_3 = timer_3 % 24;

        batch.draw(back_1, timer_1 * (Gwidth / 16) - 2 * Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_2, timer_2 * (Gwidth / 20) - 2 * Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_3, timer_3 * (Gwidth / 24) - 2 * Gwidth, -Gheight, Gwidth, Gheight * mult);

        batch.draw(back_1, timer_1 * (Gwidth / 16) - Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_2, timer_2 * (Gwidth / 20) - Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_3, timer_3 * (Gwidth / 24) - Gwidth, -Gheight, Gwidth, Gheight * mult);

        batch.draw(back_1, timer_1 * (Gwidth / 16), -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_2, timer_2 * (Gwidth / 20), -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_3, timer_3 * (Gwidth / 24), -Gheight, Gwidth, Gheight * mult);

    }


    public static TextButton genButton(String name, String internalN, float width, float height, float x, float y, int size, boolean visible)
    {
        TextButton button;
        if (visible)
        {
            TBStyle.font = getFont(size, 0);
            button = new TextButton(name, TBStyle);
        }
        else
        {
            invisTBStyle.font = getFont(size, 0);
            button = new TextButton(name, invisTBStyle);
        }

        button.setName(internalN);
        button.setBounds(x, y, width, height);
        return button;
    }

    public static TextFieldStyle getTFStyle(int size)
    {
        TFStyle.font = getFont(size, 0);
        return TFStyle;
    }

    public static SelectBoxStyle getSBStyle(int size)
    {
        SBStyle.font = getFont(size, 0);
        return SBStyle;
    }

    public static CheckBoxStyle getCBStyle(int size)
    {
        CBStyle.font = getFont(size, 0);
        return CBStyle;
    }

    public static LabelStyle getLStyle(int size, int fontID)
    {
        LStyle.font = getFont(size, fontID);
        return LStyle;
    }

    public static BitmapFont getFont(int size, int fontID)
    {
        if (font.containsKey(size + fontID + ""))
        {
            return font.get(size + fontID + "");
        }
        else
        {
            FreeTypeFontGenerator generator;
            switch (fontID)
            {
                case 0:
                default:
                    generator = new FreeTypeFontGenerator(Gdx.files.internal("menu/open-sans/OpenSans-Bold.ttf"));
                    break;
                case 1:
                    generator = new FreeTypeFontGenerator(Gdx.files.internal("menu/open-sans/OpenSans-SemiboldItalic.ttf"));
                    break;

            }
            FreeTypeFontParameter parameter = new FreeTypeFontParameter();
            parameter.size = size;
            BitmapFont fontW = generator.generateFont(parameter);
            generator.dispose();
            font.put(size + "", fontW);
            return fontW;
        }
    }
}
