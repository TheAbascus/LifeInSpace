package com.abasucs.lis.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.HashMap;
import java.util.Map;

public class UIHelper {

	public static TextureAtlas atlas;

	public static Skin uiSkin;

	public static TextButtonStyle TBStyle;
	public static TextButtonStyle invisTBStyle;
	public static TextFieldStyle TFStyle;
	public static CheckBoxStyle CBStyle;
	public static LabelStyle LStyle;
	public static Map<String,BitmapFont> font = new HashMap<String, BitmapFont>();

	public static Drawable invis;

	public static void setup()
	{
		uiSkin = new Skin(Gdx.files.internal("menu/uiskin.json"));

		TBStyle = uiSkin.get(TextButtonStyle.class);
		TFStyle = uiSkin.get(TextFieldStyle.class);
		CBStyle = uiSkin.get(CheckBoxStyle.class);
		LStyle = uiSkin.get(LabelStyle.class);

		Drawable invis = new BaseDrawable();
		invisTBStyle = new TextButtonStyle(TBStyle);
		invisTBStyle.up = invis;
		invisTBStyle.down = invis;
	}


	public static TextButton genButton(String name, String internalN, float width, float height, float x, float y, int size, boolean visible)
	{
		TextButton button = null;
		if(visible)
		{
			TBStyle.font=getFont(size);
			button = new TextButton(name, TBStyle);
		}
		else
		{
			invisTBStyle.font=getFont(size);
			button = new TextButton(name, invisTBStyle);
		}

		button.setName(internalN);
		button.setBounds(x, y, width, height);
		return button;
	}

	public static TextFieldStyle getTFStyle(int size)
	{
		TFStyle.font=getFont(size);
		return TFStyle;
	}

	public static CheckBoxStyle getCBStyle(int size)
	{
		CBStyle.font=getFont(size);
		return CBStyle;
	}
	public static LabelStyle getLStyle(int size)
	{
		LStyle.font=getFont(size);
		return LStyle;
	}

	public static BitmapFont getFont(int size)
	{
		if(font.containsKey(size+""))
		{
			return font.get(size+"");
		}
		else
		{
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("menu/open-sans/OpenSans-Bold.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = size;
			BitmapFont fontW = generator.generateFont(parameter);
			generator.dispose();
			font.put(size+"", fontW);
			return fontW;
		}
	}
}
