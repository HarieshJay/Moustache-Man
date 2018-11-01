package com.sprites;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mustacheman.game.Screens.PlayScreen;

import java.util.ArrayList;

public class lilMon extends Enemy {

    private  float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;
    private TextureAtlas atlas;
    public Body b2body;
    public World world;


    public lilMon(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        atlas = new TextureAtlas("LittleMonster.atlas");
        frames = new Array<TextureRegion>();
        walkAnimation = new Animation<TextureRegion>(0.01f, atlas.findRegions("w"));
        stateTime = 0;





    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(walkAnimation.getKeyFrame(stateTime, true));
        setBounds(getX(), getY(), 30 /MainClass.PPM, 30/MainClass.PPM);
    }

    @Override
    protected void defineEnemy() {

        BodyDef bdef = new BodyDef();
        bdef.position.set( 100/ MainClass.PPM , 300 / MainClass.PPM );


        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = screen.getWorld().createBody(bdef);



        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius( 20 /MainClass.PPM);



        fdef.shape = shape;
        b2body.createFixture(fdef);

    }


}
