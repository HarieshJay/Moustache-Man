package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;
import java.lang.*;


import sun.applet.Main;

public class LoadingScreen implements Screen {


    Batch batch;
    private MainClass game;
    Texture background;
    SpriteBatch loadingbatch;
    BitmapFont font;

    Float stopWatch;
    PlayScreen playScreen;
    Texture logo;
    SpriteBatch logobatch;





    public LoadingScreen(MainClass game) {

        stopWatch = 0f;
        background = new Texture("background.jpg");


        this.game = game;
        loadingbatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
        font = new BitmapFont();
        logo = new Texture("hariesh.png");
        logobatch = new SpriteBatch();






    }


    @Override
    public void show() {

    }

    public void update(Float dt){
        stopWatch += dt;

        if (stopWatch > 5){

            game.setScreen(new PlayScreen(game));

        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        loadingbatch.begin();

        //loadingbatch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.getData().setScale(1);
        font.setColor(Color.WHITE);



        logobatch.begin();
        logobatch.draw(logo, Gdx.graphics.getWidth()/2 - (Gdx.graphics.getWidth() /3)/2, Gdx.graphics.getHeight()/2  - (Gdx.graphics.getWidth() /3)/2, Gdx.graphics.getWidth() /3, Gdx.graphics.getWidth() /3);
        logobatch.end();


        loadingbatch.end();




    }

    @Override
    public void resize(int width, int height) {

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

    }
}
