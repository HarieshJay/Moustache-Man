package com.MainClass.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class lilMon extends Enemy {

    private  float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;
    private TextureAtlas atlas;
    public Body b2body;
    public World world;
    private boolean setToDestroy;
    private boolean destroyed;
    float x;
    float y;


    public lilMon(PlayScreen screen, float x, float y, Hud hud) {
        super(screen, x, y, hud);
        atlas = new TextureAtlas("LittleMonster.atlas");
        frames = new Array<TextureRegion>();
        walkAnimation = new Animation<TextureRegion>(0.01f, atlas.findRegions("w"));
        stateTime = 0;
        setToDestroy = false;
        destroyed = false;
        world = screen.getWorld();








    }

    public void update(float dt){
        stateTime += dt;


        if(setToDestroy && !destroyed){


            screen.getWorld().destroyBody(b2body);
            destroyed = true;

            setBounds(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2 - 5/MainClass.PPM , 40/MainClass.PPM, 20/MainClass.PPM);
            stateTime = 0;






        }
        else if (!destroyed) {
            setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2 - 5/MainClass.PPM);
            setRegion(walkAnimation.getKeyFrame(stateTime, true));
            b2body.setLinearVelocity(velocity);


        }

    }


    @Override
    protected void defineEnemy() {
        setBounds(getX(), getY(), 40 /MainClass.PPM, 40 / MainClass.PPM);

        BodyDef bdef = new BodyDef();
        bdef.position.set( getX(), getY());



        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = screen.getWorld().createBody(bdef);




        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius( 25 /MainClass.PPM);



        fdef.shape = shape;

        fdef.filter.categoryBits = MainClass.ENEMY_BIT;
        fdef.filter.maskBits = MainClass.ENEMYBORDER_BIT | MainClass.MAN_BIT | MainClass.GROUND_BIT |MainClass.OBJECT_BIT;
        b2body.createFixture(fdef).setUserData(this);


        //create Head
        PolygonShape head = new PolygonShape();
        Vector2[] veritice = new Vector2[4];
        veritice[0] = new Vector2(-10, 30).scl(1 /MainClass.PPM);
        veritice[1] = new Vector2(10, 30).scl(1 /MainClass.PPM);
        veritice[2] = new Vector2(-30, 25).scl(1 /MainClass.PPM);
        veritice[3] = new Vector2(30, 25).scl(1 /MainClass.PPM);
        head.set(veritice);
        fdef.isSensor = true;

        fdef.shape = head;

        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = MainClass.ENEMY_HEAD_BIT;
        fdef.filter.maskBits = MainClass.GROUND_BIT | MainClass.MAN_BIT ;
        b2body.createFixture(fdef).setUserData(this);


    }

    public void draw(Batch batch){
        if(!destroyed || stateTime < 1)

            super.draw(batch);
    }

    @Override
    public void hitOnHead() {
        setToDestroy = true;


    }
    public void onHit(){}


    public static class WorldContactListener implements ContactListener {
        public PlayScreen playScreen;

        public WorldContactListener(PlayScreen screen){
            playScreen = screen;



        }



        @Override
        public void beginContact(Contact contact) {


            Fixture fixA = contact.getFixtureA();
            Fixture fixB = contact.getFixtureB();

            /*if(fixA.getUserData() == "playerbody" || fixB.getUserData() == "playerbody"){
                Fixture player = fixA.getUserData() == "playerbody" ? fixA : fixB;
                Fixture object = player == fixA ? fixB : fixA;

                if (object.getUserData() instanceof InteractiveTileObject){
                    ((InteractiveTileObject) object.getUserData()).onHeadHit();            }
            } */





            int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;




            switch (cDef) {
                case MainClass.ENEMY_HEAD_BIT | MainClass.MAN_BIT:
                    if (fixA.getFilterData().categoryBits == MainClass.ENEMY_HEAD_BIT) {
                        ((Enemy) fixA.getUserData()).hitOnHead();
                        fixA.getFilterData().categoryBits = MainClass.DESTROYED_BIT;
                        fixA.getFilterData().maskBits = MainClass.NOTHING_BIT;
                    } else if (fixB.getFilterData().categoryBits == MainClass.ENEMY_HEAD_BIT) {
                        ((Enemy) fixB.getUserData()).hitOnHead();
                        fixB.getFilterData().categoryBits = MainClass.DESTROYED_BIT;
                        fixB.getFilterData().maskBits = MainClass.NOTHING_BIT;
                    }
                    break;


                /*case (MainClass.COIN_BIT | MainClass.MAN_HEAD_BIT) :
                    if (fixA.getFilterData().categoryBits == MainClass.COIN_BIT)
                        ((Coin) fixA.getUserData()).onHit();

                    else if (fixB.getFilterData().categoryBits == MainClass.COIN_BIT)
                        ((Coin) fixB.getUserData()).onHit(); */




                case MainClass.GROUND_BIT | MainClass.MAN_BIT:
                    playScreen.currentjump = 0;

            }


            if (doesCollide(fixA,fixB, MainClass.ENEMY_BIT, MainClass.ENEMYBORDER_BIT)) {


                    if (fixA.getFilterData().categoryBits == MainClass.ENEMY_BIT)
                        ((Enemy) fixA.getUserData()).reverseVelocity(true, false);


                    else if (fixB.getFilterData().categoryBits == MainClass.ENEMY_BIT)
                        ((Enemy) fixB.getUserData()).reverseVelocity(true, false);


                }

            if (doesCollide(fixA,fixB, MainClass.MAN_BIT, MainClass.COIN_BIT) || doesCollide(fixA,fixB, MainClass.MAN_HEAD_BIT, MainClass.COIN_BIT)){

                    if (fixA.getFilterData().categoryBits == MainClass.COIN_BIT)
                        ((Coin) fixA.getUserData()).onHit();


                    else if (fixB.getFilterData().categoryBits == MainClass.COIN_BIT)
                        ((Coin) fixB.getUserData()).onHit();

                }



            if (doesCollide(fixA,fixB, MainClass.MAN_BIT, MainClass.ENEMY_BIT)){

                if (fixA.getFilterData().categoryBits == MainClass.MAN_BIT) {
                    ((MoustacheMan) fixA.getUserData()).onHit();

                }

                else if (fixB.getFilterData().categoryBits == MainClass.MAN_BIT){
                    ((MoustacheMan) fixB.getUserData()).onHit();
                }

            }

            if (doesCollide(fixA, fixB, MainClass.MAN_BIT , MainClass.EXIT_BIT)){
                playScreen.gameoverb =true;
                playScreen.gameoverb = true;
                playScreen.isalive = true;


            }

            if (doesCollide(fixA,fixB, MainClass.MAN_BIT, MainClass.GROUND_BIT)){

                if (fixA.getFilterData().categoryBits == MainClass.MAN_BIT) {


                }

                else if (fixB.getFilterData().categoryBits == MainClass.MAN_BIT){


                }

            }



            }






        @Override
        public void endContact(Contact contact) {

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }

        public boolean doesCollide(Fixture fixA, Fixture fixB, short bit1, short bit2){

            if ((fixB.getFilterData().categoryBits == bit1 && fixA.getFilterData().categoryBits == bit2) || (fixA.getFilterData().categoryBits == bit1 && fixB.getFilterData().categoryBits == bit2)){
                return true;
            }

            else
                return false;



        }
    }

    public static class B2WorldCreator {
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
                fdef.friction = 0;
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
}
