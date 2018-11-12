package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOver implements Screen {
    Batch batch;
    Texture texture;

    public GameOver(MainClass game) {
        texture = new Texture("landscape.png");
        batch = new SpriteBatch();


    }


    @Override
    public void show() {

    }

    public void update(float dt){

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(texture,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();


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
