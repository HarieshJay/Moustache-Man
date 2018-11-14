package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOver implements Screen {
    Batch batch;
    Texture texture;
    private MainClass game;
    BitmapFont font;

    public GameOver(MainClass game) {

        this.game = game;
        texture = new Texture("landscape.png");
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font.fnt"),
                Gdx.files.internal("font.png"), false);


    }


    @Override
    public void show() {

    }

    public void update(float dt){


    }

    @Override
    public void render(float delta) {
        update(delta);
        batch.begin();
        batch.draw(texture,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.getData().setScale(10);

        font.draw(batch, "Nice Try!!", Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/ 2);

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
