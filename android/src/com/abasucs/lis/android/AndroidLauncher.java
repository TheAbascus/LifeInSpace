package com.abasucs.lis.android;

import android.os.Bundle;

import com.abasucs.lis.IActivityRequestHandler;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.abasucs.lis.Main;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler
{


	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new Main(this, Main.PLATFORM_ANDROID), config);
	}


}
