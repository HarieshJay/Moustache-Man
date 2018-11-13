package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingScreen implements Screen {

    Timer timer;
    Batch batch;
    Texture texture;


    public LoadingScreen(MainClass game) {

        texture = new Texture("landscape.png");
        timer = new Timer();
        //TimerTask task = new TimerTask(){

        //}
        //timer.schedule(game.setScreen(null), new Long(100));


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
