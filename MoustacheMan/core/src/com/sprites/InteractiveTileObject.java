package com.sprites;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mustacheman.game.Screens.PlayScreen;

import java.util.ArrayList;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(PlayScreen screen, Rectangle bounds){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth()/2) / MainClass.PPM, ( bounds.getY() + bounds.getHeight()/2) / MainClass.PPM );
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/2 / MainClass.PPM, bounds.getHeight()/2 / MainClass.PPM);
        fdef.shape = shape;
        fdef.isSensor = true; // Used because of Coin, Might need to move this around later
        //Can only change fdef before it is used in body.createFixture(fdef);
        //ex. isSenor will return a bool if you use it after fixture = body.createFixture(fdef);

        fixture = body.createFixture(fdef);


    }
    public abstract void onHeadHit();
    public void setCategoryFilter(short filterbit){
        Filter filter = new Filter();
        filter.categoryBits = filterbit;
        fixture.setFilterData(filter);


    }

    public void setFilter(short Maskbits, short filterbit){
        Filter filter = new Filter();

        filter.categoryBits = filterbit;
        filter.maskBits = MainClass.MAN_BIT | MainClass.MAN_HEAD_BIT;
        fixture.setFilterData(filter);


    }

    public TiledMapTileLayer.Cell  getCell(int i){
        ArrayList<TiledMapTileLayer.Cell> arrayList = new ArrayList<TiledMapTileLayer.Cell>();
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(2);
        arrayList.add(layer.getCell((int) ((body.getPosition().x * MainClass.PPM / 16 - 1  ) ), (int) (body.getPosition().y * MainClass.PPM / 16)));
        arrayList.add(layer.getCell((int) ((body.getPosition().x * MainClass.PPM / 16) ), (int) (body.getPosition().y * MainClass.PPM / 16)));
        arrayList.add(layer.getCell((int) ((body.getPosition().x * MainClass.PPM / 16) ), (int) (body.getPosition().y * MainClass.PPM / 16 - 1)));
        arrayList.add(layer.getCell((int) ((body.getPosition().x * MainClass.PPM / 16 - 1) ), (int) (body.getPosition().y * MainClass.PPM / 16 - 1)));

        if (i == 0){
            return arrayList.get(0);
        }
        if (i == 1){
            return arrayList.get(1);
        }
        if (i == 2){
            return arrayList.get(2);
        }
        if (i == 3){
            return arrayList.get(3);
        }
        else return arrayList.get(0);


    }




}
