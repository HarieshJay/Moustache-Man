package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;
import java.lang.*;


import sun.applet.Main;

public class LoadingScreen implements Screen {


    Batch batch;
    private MainClass game;
    Texture lscreen;
    SpriteBatch loadingbatch;

    Float stopWatch;
    PlayScreen playScreen;




    public LoadingScreen(MainClass game) {

        stopWatch = 0f;
        lscreen = new Texture("landscape.png");
        this.game = game;
        loadingbatch = new SpriteBatch();
        lscreen = new Texture("loadingscreen.jpg");





    }


    @Override
    public void show() {

    }

    public void update(Float dt){
        stopWatch += dt;

        if (stopWatch > 0.5){

            game.setScreen(new PlayScreen(game));

        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        loadingbatch.begin();
        loadingbatch.draw(lscreen, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
