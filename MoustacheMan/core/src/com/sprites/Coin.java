package com.sprites;

import com.MainClass.game.MainClass;
import com.Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mustacheman.game.Screens.PlayScreen;

public class Coin extends InteractiveTileObject {
    boolean destroy = true;


    public Coin(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MainClass.COIN_BIT);








    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "Collision");



    }

    public void onHit() {
        setCategoryFilter(MainClass.DESTROYED_BIT);
        int[] a = new int[2];
        getCell(0).setTile(null);
        getCell(1).setTile(null);
        getCell(2).setTile(null);
        getCell(3).setTile(null);
        Hud.addscore(200);







    }

    public void update(float dt) {


    }
}


