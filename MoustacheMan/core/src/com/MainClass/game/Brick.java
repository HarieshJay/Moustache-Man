package com.MainClass.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Brick extends InteractiveTileObject {

    public Brick(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MainClass.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick","Collision");
        Hud.addscore(200);
        setCategoryFilter(MainClass.DESTROYED_BIT);


    }
    public void onHit(){}
}
