package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Color;

import sun.applet.Main;

public class GameOver implements Disposable {
    Batch batch;
    Texture texture;
    private MainClass game;
    BitmapFont font;
    public Stage gameoStage;
    private Viewport viewport;
    Table table;
    Texture restart;
    Image button;

    public GameOver(SpriteBatch batch, int score, MainClass game) {


        this.game = game;
        this.batch = batch;
        onCreate();






    }



    public void onCreate(){
        texture = new Texture("landscape.png");
        font = new BitmapFont(Gdx.files.internal("font.fnt"),
                Gdx.files.internal("font.png"), false);

        viewport = new FitViewport(MainClass.V_Width, MainClass.V_Height, new OrthographicCamera());
        gameoStage = new Stage(viewport, batch);


        restart = new Texture("restart.png");
        button = new Image(restart);

        button.setSize(75, 75);
        button.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen( game));
                return true;

            }


        });





        table = new Table();

        table.setFillParent(true);

        table.center().center();
        table.add(button);

        gameoStage.addActor(table);
        Gdx.input.setInputProcessor(gameoStage);

    }

    public void render(float delta){

        gameoStage.act();
        gameoStage.draw();
    }

    public void update(float dt) {


    }


   /* public void render(float delta) {
        update(delta);
        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.getData().setScale(10);
        tabel.draw(batch, 0);

        font.draw(batch, "Nice Try!!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        batch.end();

    } */

    @Override
    public void dispose() {
        gameoStage.dispose();

    }
}