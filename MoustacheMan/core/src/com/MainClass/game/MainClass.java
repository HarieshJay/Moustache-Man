package com.MainClass.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.mustacheman.game.Screens.PlayScreen;


public class MainClass extends Game {
	public SpriteBatch batch;
	public static final int V_Width = 1500;
	public static final int V_Height =  768;

	public static final float PPM = 100;


	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short MAN_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MAN_HEAD_BIT = 512;
	public static final short FIREBALL_BIT = 1024;






	@Override
	public void create () {

		setScreen(new PlayScreen(this));



		//music = MainClass.manager.get("sounds/music.ogg", Music.class);

	}

	@Override
	public void render () {
		super.render();
        //manager.update();


	}
	
	@Override
	public void dispose () {

	}
}
