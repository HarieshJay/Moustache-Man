package com.sprites;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class MoustacheMan extends Sprite {
    public World world;
    public Body b2body;

    public  MoustacheMan(World world){
        this.world = world;
        defineMoustacheMan();

    }

    public void defineMoustacheMan(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 , 100 );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(50 / MainClass.PPM );


        fdef.shape = shape;
        b2body.createFixture(fdef);
    }



}
