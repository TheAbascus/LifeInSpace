package com.abasucs.lis;

/**
 * Created by Abasucs on 23.12.2015.
 */
public final class Constants
{

    public static final String[] resolutions = {"1600x900", "1440x900", "1280x720", "1280x600", "1024x768","800x600"};

    public static final float TIME_STEP = 1/45f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    public static final String[] RESOURCES = {"wood", "iron", "silicon", "water", "gold", "meat", "corn", "biomatter", "plasma"};



    public static final float ROCKETHEIGHT = 25;
    public static final float ROCKETSIZE = 7;
    public static final float PLANETRADIUS = 100;
    public static final float PLANETGRAV = 20;
    public static final double DEGSIZE = Constants.PLANETRADIUS * Math.sin(Math.toRadians(1));

    public static final float PLAYERHEIGHT = 10;
    public static final float PLAYERSIZE = 5;
    public static final float PLAYERSPEED = 30;
    public static final float PLAYERJETSPEED = 25;
    public static final float PLAYERMAXJUMP = 50;

    public static final float LANDFORMSIZEDEG = 20;
    public static final float LANDFORMSIZE = (float)DEGSIZE*LANDFORMSIZEDEG;
    public static final float LANDFORMHEIGHT = 25;
    public static final float MOUNTAINHEIGHT = 30;

    public static final int WORLDX = 1000;
    public static final int WORLDY = 1000;

    public static final String LEVELPATH = "inGame/worlds/";
    public static final boolean DEBUG = true;
}
