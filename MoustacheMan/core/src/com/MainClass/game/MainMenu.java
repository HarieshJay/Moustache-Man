package com.MainClass.game;

import com.MainClass.game.MainClass;
import com.MainClass.game.Menu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen {

    private MainClass game;
    Texture background;
    SpriteBatch batch2;


    Menu menu;
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();




    public MainMenu(MainClass game) {
        this.game = game;
        game.batch = new SpriteBatch();
        background = new Texture("cave.png");
        menu = new Menu(game, this);
        batch2 = new SpriteBatch();

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();







    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        batch2.begin();
        batch2.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch2.end();



        //game.batch.begin();
        //game.batch.draw(background, 0, 0, width, height);
        //game.batch.end();

        //menu.stage.draw();
        menu.render(delta);
        game.batch.setProjectionMatrix(menu.stage.getCamera().combined);}






    @Override
    public void resize(int width, int height) {

        menu.stage.getViewport().update(width,height);
        this.width = width;
        this.height = height;




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
        menu.dispose();
        batch2.dispose();
        background.dispose();

    }
}