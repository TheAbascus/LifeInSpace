package com.abasucs.lis.game;

import com.abasucs.lis.game.Landforms.Landform;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Abascus on 25.12.2015.
 */
public class LevelIO
{

    public static Level loadLevel(String path) throws Exception
    {
        FileHandle handle = Gdx.files.internal(path);
        String[] data = handle.readString().split("\n");
        for (int i = 0; i < data.length; i++)
        {
            data[i] = data[i].split("//")[0];
        }
        Array.ArrayIterator<String> iterator = new Array.ArrayIterator(new Array(data));

        int line = 4;
        Level level = new Level();
        level.name = iterator.next();
        level.timeLeft = Integer.parseInt(iterator.next());
        level.playerPlanet = Integer.parseInt(iterator.next());
        level.player = new Player();
        level.player.pos = Integer.parseInt(iterator.next());

        String current;

        while (iterator.hasNext())
        {
            line++;
            current = iterator.next();
            if (current.contentEquals("{"))
            {
                Planet p = new Planet();
                line++;
                p.x = Integer.parseInt(iterator.next());
                line++;
                p.y = Integer.parseInt(iterator.next());
                line++;
                p.rocketPos = Integer.parseInt(iterator.next());
                while (iterator.hasNext())
                {
                    line++;
                    current = iterator.next();
                    if (current.contentEquals("["))
                    {
                        line += 4;
                        p.addLandform(Landform.getLandform(iterator.next(), Integer.parseInt(iterator.next()), Integer.parseInt(iterator.next()), Integer.parseInt(iterator.next())));
                        line++;
                        if (!iterator.next().contentEquals("]"))
                        {
                            throw new Exception("Defect Landform on line" + line + "of" + path);
                        }
                    }
                    else if (current.contentEquals("}"))
                    {
                        level.addPlanet(p);
                        break;
                    }
                    else if(current.matches("[\r\n]+"))
                    {}
                    else
                    {
                        throw new Exception("Defect Planet on line " + line + " of " + path+":  ");
                    }
                }

            }
            else if (current.startsWith("+"))
            {
                String[] res = current.replace("+", "").split(" ");
                level.addResource(res[0], Integer.parseInt(res[1]));
            }
            else if (current.startsWith("-"))
            {
                String[] res = current.replace("-", "").split(" ");
                level.addQuest(res[0], Integer.parseInt(res[1]));
            }
            else if(current.matches("[\r\n]+"))
            {}
            else
            {
                throw new Exception("Defect Level on line " + line + " of" + path);
            }
        }

level.postLoad();
        return level;
    }


}
