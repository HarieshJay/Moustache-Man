package com.MainClass.game;

import com.MainClass.game.Hud;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.MainClass.game.PlayScreen;
import com.badlogic.gdx.utils.Array;

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;

    public Enemy(PlayScreen screen, float x, float y, Hud hud){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(3, -2);


    }



    protected abstract void defineEnemy();
    public abstract void hitOnHead();
    public abstract void onHit();

    public void reverseVelocity( boolean x, boolean y){
        if(x){
            velocity.x = -velocity.x;
        }
        if (y){
            velocity.y = -velocity.y;
        }

    }

    public abstract void update(float dt);


}
