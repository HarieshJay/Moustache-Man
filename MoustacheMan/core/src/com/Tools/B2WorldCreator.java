package com.Tools;

import com.MainClass.game.MainClass;
import com.Scenes.Hud;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mustacheman.game.Screens.PlayScreen;
import com.sprites.Coin;
import com.sprites.MoustacheMan;
import com.sprites.lilMon;

import java.util.ArrayList;

public class B2WorldCreator {
    private Array<lilMon> monsters;


    public B2WorldCreator(PlayScreen screen, Hud hud) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();


        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;







        //Ground
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MainClass.PPM, ( rect.getY() + rect.getHeight()/2) / MainClass.PPM );
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2 / MainClass.PPM, rect.getHeight()/2 / MainClass.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MainClass.GROUND_BIT;
            fdef.filter.maskBits = MainClass.MAN_BIT | MainClass.ENEMY_BIT |MainClass.MAN_HEAD_BIT;
            body.createFixture(fdef).setUserData(this);


        }

        //Borders
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MainClass.PPM, ( rect.getY() + rect.getHeight()/2) / MainClass.PPM );
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2 / MainClass.PPM, rect.getHeight()/2 / MainClass.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MainClass.OBJECT_BIT;
            fdef.filter.maskBits = MainClass.MAN_BIT;
            body.createFixture(fdef).setUserData(this);



        }

        //EnemyBorder
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MainClass.PPM, ( rect.getY() + rect.getHeight()/2) / MainClass.PPM );


            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 / MainClass.PPM, rect.getHeight()/2 / MainClass.PPM);
            fdef.shape = shape;
            fdef.filter.maskBits = MainClass.ENEMY_BIT;
            fdef.filter.categoryBits = MainClass.ENEMYBORDER_BIT;
            body.createFixture(fdef).setUserData(this);


        }


        //Exit
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MainClass.PPM, ( rect.getY() + rect.getHeight()/2) / MainClass.PPM );
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2 / MainClass.PPM, rect.getHeight()/2 / MainClass.PPM);
            fdef.shape = shape;
            fdef.isSensor = true;
            fdef.filter.maskBits = MainClass.MAN_BIT | MainClass.MAN_HEAD_BIT;
            fdef.filter.categoryBits = MainClass.EXIT_BIT;
            body.createFixture(fdef).setUserData(this);


        }


        //Coin
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            map.getLayers().get(5).getObjects().remove(object);
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            new Coin(screen, rect);


        }

        monsters = new Array<lilMon>();
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class))
        {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            monsters.add(new lilMon(screen, rect.getX() /MainClass.PPM, rect.getY()/MainClass.PPM, hud));


        }









    }

    public Array<lilMon> getMonsters(){
        return monsters;
    }




}
