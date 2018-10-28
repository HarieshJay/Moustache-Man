package com.sprites;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mustacheman.game.Screens.PlayScreen;

import sun.applet.Main;


public class MoustacheMan extends Sprite {

    public World world;
    public enum State {FALLING, JUMPING, STANDING, RUNNING };
    public State currentState;
    public State previousState;
    public Body b2body;
    private Animation<TextureRegion> stand;
    TextureRegion run;
    TextureRegion jump;
    float stateTime;
    Animation<TextureRegion> manRun;
    Animation<TextureRegion> manJump;
    private boolean rightrun;



    public TextureAtlas atlas;



    public  MoustacheMan(World world, PlayScreen screen ){

        super(screen.getTextureAtlas().findRegion("run/run"));
        atlas = new TextureAtlas("RunJumpKoRoll.atlas");
        stateTime = 0;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stand  = new com.badlogic.gdx.graphics.g2d.Animation(1/15f, atlas.findRegions("run/run"), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        jump = new TextureRegion(new TextureRegion(screen.getTextureAtlas().findRegion("jump/j")));
        manRun = new com.badlogic.gdx.graphics.g2d.Animation(1/15f, atlas.findRegions("run/run"), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        manJump = new com.badlogic.gdx.graphics.g2d.Animation(1/15f, atlas.findRegions("jump/j"), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        this.world = world;
        rightrun = true;
        defineMoustacheMan();
        setBounds(0, 0, 60 /MainClass.PPM , 90/ MainClass.PPM );




    }
    public void update(float dt){

        setRegion(getframe(dt));
        setBounds(0, 0, 60 /MainClass.PPM , 90/ MainClass.PPM );

        setPosition(b2body.getPosition().x - getWidth() / 2f, b2body.getPosition().y  - getHeight() / 2f);







    }


    public void defineMoustacheMan(){
        BodyDef bdef = new BodyDef();
        bdef.position.set( 100/ MainClass.PPM , 300 / MainClass.PPM );




        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);



        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius( 50 /MainClass.PPM);



        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-10 / MainClass.PPM, 60/MainClass.PPM), new Vector2(10 / MainClass.PPM, 60/MainClass.PPM));
        fdef.shape = head;
        b2body.createFixture(fdef).setUserData("head");
        fdef.isSensor = true;
    }

    public State getState() {
        if (b2body.getLinearVelocity().y > 0 || b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING){
            return State.JUMPING;
        }
        else if (b2body.getLinearVelocity().y < 0){
            return State.FALLING;
        }
        else if (b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else {
            return State.STANDING;
        }

    }

    public TextureRegion getframe(float dt){

        currentState = getState();
        TextureRegion animation;
        switch(currentState){
            case JUMPING:
                animation = manJump.getKeyFrame(stateTime);
                break;
            case RUNNING:
                animation = manRun.getKeyFrame(stateTime, true);
                break;
            case STANDING:
            case FALLING:
            default:
                animation = stand.getKeyFrame(stateTime);

                break;

        }
        if ((b2body.getLinearVelocity().x < 0 || !rightrun) && !animation.isFlipX()){
            animation.flip(true, false);
            rightrun = false;

        }
        else if ((b2body.getLinearVelocity().x > 0 || rightrun) && animation.isFlipX()){
            animation.flip(true, false);
            rightrun = true;
        }
        stateTime = currentState == previousState ? stateTime + dt : 0;
        previousState = currentState;

        return animation;


    }






    }