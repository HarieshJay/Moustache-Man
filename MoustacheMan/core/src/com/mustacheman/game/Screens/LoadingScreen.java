package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;
import java.lang.*;


public class LoadingScreen implements Screen {

    private final MainClass game;
    Texture background;

    BitmapFont font;

    Float stopWatch;

    Texture logo;

    int Posx;
    int Posy;
    int Pwidth;
    int PHeight;










    public LoadingScreen(MainClass game) {

        stopWatch = 0f;
        background = new Texture("background.jpg");


        this.game = game;
        game.batch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
        font = new BitmapFont();
        logo = new Texture("hariesh.png");

        Posx = Gdx.graphics.getWidth()/2 - (Pwidth)/2;
        Posy = Gdx.graphics.getHeight()/2  - (PHeight)/2;
        Pwidth = Gdx.graphics.getWidth()/3;
        PHeight = Gdx.graphics.getHeight()/2;





    }


    @Override
    public void show() {

    }

    public void update(Float dt){
        stopWatch += dt;

        if (stopWatch > 400){

            game.setScreen(new MainMenu(game));

        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        //loadingbatch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        font.getData().setScale(1);
        font.setColor(Color.WHITE);



        game.batch.begin();
        game.batch.draw(logo, Posx, Posy , Pwidth, PHeight);
        game.batch.end();



    }

    @Override
    public void resize(int width, int height) {

        Posx = width/2 - (Pwidth)/2;
        Posy = height/2  - (PHeight)/2;
        Pwidth = 350;
        PHeight = 400;



    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        game.batch.dispose();
        font.dispose();


    }
}
