package com.abasucs.lis.menu;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;

public class UIHelper {

	public static TextureAtlas atlas;

	public static Skin uiSkin;

	public static TextButtonStyle TBStyle;
	public static TextFieldStyle TFStyle;
	public static CheckBoxStyle CBStyle;
	public static LabelStyle LStyle;
	public static Map<String,BitmapFont> font = new HashMap<String, BitmapFont>();

	public static void setup()
	{
		uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

		TBStyle = uiSkin.get(TextButtonStyle.class);
		TFStyle = uiSkin.get(TextFieldStyle.class);
		CBStyle = uiSkin.get(CheckBoxStyle.class);
		LStyle = uiSkin.get(LabelStyle.class);

	}


	public static TextButton genButton(String name, String internalN, float width, float height, float x, float y, int size)
	{
		TBStyle.font=getFont(size);
		TextButton button = new TextButton(name, TBStyle);
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
