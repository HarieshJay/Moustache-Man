package com.sprites;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mustacheman.game.Screens.PlayScreen;
import com.sun.org.apache.bcel.internal.Constants;

import sun.applet.Main;


public class MoustacheMan extends Sprite {

    public World world;
    public enum State {FALLING, JUMPING, STANDING, RUNNING , DEAD}
    public State currentState;
    public State previousState;
    public Body b2body;
    private TextureRegion stand;
    TextureRegion run;
    TextureRegion jump;
    float stateTime;
    Animation<TextureRegion> manRun;
    Animation<TextureRegion> manJump;
    Animation<TextureRegion> manKO;
    TextureRegion standimg;
    private boolean rightrun;
    public boolean dead = false;



    public TextureAtlas atlas;




    public  MoustacheMan( PlayScreen screen ){

        super(screen.getTextureAtlas().findRegion("run/run"));
        atlas = new TextureAtlas("RunJumpKoRoll.atlas");

        stateTime = 0;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stand  = new TextureRegion(atlas.findRegion("run/run",7));
        jump = new TextureRegion(new TextureRegion(screen.getTextureAtlas().findRegion("jump/j")));
        manRun = new com.badlogic.gdx.graphics.g2d.Animation(1/50f, atlas.findRegions("run/run"), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        manJump = new com.badlogic.gdx.graphics.g2d.Animation(1/50f, atlas.findRegions("jump/j"), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        manKO = new Animation<TextureRegion>(1/20f, atlas.findRegions("KO/ko"), Animation.PlayMode.LOOP);
        this.world = screen.getWorld();
        rightrun = true;
        defineMoustacheMan();
        setBounds(0, 0, 66 /MainClass.PPM , 103/ MainClass.PPM );




    }



    public void update(float dt){


            setRegion(getframe(dt));
            setPosition(b2body.getPosition().x - getWidth() / 2f, b2body.getPosition().y - getHeight() / 2f + 8 / MainClass.PPM);




    }

    /*public void draw(Batch batch){
        if(!destroyed || stateTime < 1)

            super.draw(batch);


    }*/





    public void defineMoustacheMan(){
        BodyDef bdef = new BodyDef();
        bdef.position.set( 100/ MainClass.PPM , 300 / MainClass.PPM );


        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);



        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        //Create body
        shape.setRadius( 20 /MainClass.PPM);

        fdef.filter.categoryBits = MainClass.MAN_BIT;

        fdef.filter.maskBits = MainClass.GROUND_BIT | MainClass.BRICK_BIT | MainClass.ENEMY_BIT |
                MainClass.COIN_BIT |
                MainClass.OBJECT_BIT|
                MainClass.ENEMY_HEAD_BIT;
        shape.setPosition(new Vector2(0, -20/MainClass.PPM));

        fdef.shape = shape;


        b2body.createFixture(fdef).setUserData(this);

        //Create head
        FixtureDef bodyfdef = new FixtureDef();
        shape.setRadius(35 /MainClass.PPM);
        shape.setPosition(new Vector2(0, 20/MainClass.PPM));
        bodyfdef.shape = shape;
        fdef.filter.categoryBits = MainClass.MAN_HEAD_BIT;
        fdef.friction = 0.2f;
        fdef.filter.maskBits = MainClass.GROUND_BIT | MainClass.BRICK_BIT | MainClass.ENEMY_BIT |
                MainClass.COIN_BIT |
                MainClass.OBJECT_BIT |
                MainClass.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);









    }



    public State getState() {
        if (dead){
            return State.DEAD;


        }

        else if (b2body.getLinearVelocity().y > 0 || b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING){
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
                setBounds(0, 0, 57 /MainClass.PPM , 102/ MainClass.PPM );
                break;
            case RUNNING:
                animation = manRun.getKeyFrame(stateTime, true);
                setBounds(0, 0, 66 /MainClass.PPM , 103/ MainClass.PPM );
                break;
            case STANDING:
                animation = stand;
                setBounds(0, 0, 66 /MainClass.PPM , 103/ MainClass.PPM );
                break;
            case DEAD:
                animation = manKO.getKeyFrame(stateTime,false);
                setBounds(b2body.getPosition().x - getWidth() / 2f, b2body.getPosition().y - getHeight() / 2f + 8 / MainClass.PPM, 135 /MainClass.PPM , 115/MainClass.PPM );

                break;

            case FALLING:


            default:
                animation = stand;
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

    public void onHit(){
        dead = true;
        Filter filter = new Filter();
        filter.maskBits = MainClass.NOTHING_BIT;
        for (Fixture fixture : b2body.getFixtureList()){
            fixture.setFilterData(filter);
        }



    }






    }