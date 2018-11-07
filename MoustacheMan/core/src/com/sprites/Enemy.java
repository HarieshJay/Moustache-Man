package com.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.mustacheman.game.Screens.PlayScreen;

import javax.print.attribute.standard.PrinterLocation;

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;

    public Enemy(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(2, -2);


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


}
