package com.MainClass.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Scenes.GameOver;
import com.mustacheman.game.Screens.LoadingScreen;
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
	public static final short ENEMYBORDER_BIT = 2048;
	public static final short EXIT_BIT = 4096;
	PlayScreen screen;
	GameOver gameOver;
	LoadingScreen loadingScreen;







	@Override
	public void create () {
		batch = new SpriteBatch();
		loadingScreen = new LoadingScreen(this);
		//screen = new PlayScreen(this);


		setScreen(loadingScreen);

		//music = MainClass.manager.get("sounds/music.ogg", Sound.class);

	}

	public void screenUpdate(){

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
