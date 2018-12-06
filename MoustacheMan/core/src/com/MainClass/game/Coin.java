package com.MainClass.game;

import com.badlogic.gdx.math.Rectangle;

public class Coin extends InteractiveTileObject {
    boolean destroy = true;


    public Coin(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);



        setFilter(MainClass.MAN_BIT, MainClass.COIN_BIT);



    }

    @Override
    public void onHeadHit() {




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


