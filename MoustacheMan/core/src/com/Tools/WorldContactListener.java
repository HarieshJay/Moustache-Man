package com.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sprites.Coin;
import com.sprites.Enemy;
import com.sprites.InteractiveTileObject;
import com.MainClass.game.MainClass;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;


        if(fixA.getUserData() == "player" || fixB.getUserData() == "player"){
            Fixture player = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture object = player == fixA ? fixB : fixA;

            if (object.getUserData() instanceof InteractiveTileObject){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();            }
        }


        switch (cDef){
            case MainClass.ENEMY_HEAD_BIT | MainClass.MAN_BIT:
                if (fixA.getFilterData().categoryBits == MainClass.ENEMY_HEAD_BIT){
                    ( (Enemy)fixA.getUserData()).hitOnHead();
                       fixA.getFilterData().categoryBits = MainClass.DESTROYED_BIT;
                       fixA.getFilterData().maskBits = MainClass.NOTHING_BIT;}
                else if (fixB.getFilterData().categoryBits == MainClass.ENEMY_HEAD_BIT)
                {( (Enemy)fixB.getUserData()).hitOnHead();
                    fixB.getFilterData().categoryBits = MainClass.DESTROYED_BIT;
                    fixB.getFilterData().maskBits = MainClass.NOTHING_BIT;}
                break;


            case MainClass.COIN_BIT | MainClass.MAN_BIT:
                if (fixA.getFilterData().categoryBits == MainClass.COIN_BIT)
                    ( (Coin)fixA.getUserData()).onHit();

                else if (fixB.getFilterData().categoryBits == MainClass.COIN_BIT)
                    ( (Coin)fixB.getUserData()).onHit();

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
}
