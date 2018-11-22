package com.Scenes;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mustacheman.game.Screens.PlayScreen;

public class Menu implements Disposable {


    Batch batch;
    Texture texture;
    private MainClass game;

    public Stage stage;
    private Viewport viewport;

    TextButton level1;
    TextButton level2;

    Skin skin;

    Label message;

    Window window;


    public Menu( MainClass game) {


        this.game = game;
        this.batch = batch;

        onCreate();






    }



    public void onCreate(){

        skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
        window = new Window("Level Selection", skin);

        //redo = new TextButton("restart", skin);


        viewport = new FitViewport(MainClass.V_Width, MainClass.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        level1 = new TextButton("Level 1", skin);
        level2 = new TextButton("Level 2", skin);

        level1.setSize(150, 150);
        level2.setSize(150, 150);

        message = new Label("Choose a level\nPick 2 for a challenge", skin);

        message.setFontScale(2);

        level1.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen( game, "level1.tmx"));
                return true;

            }


        });

        level2.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen( game, "level2.tmx"));
                return true;

            }


        });


        window.setSize(600,600);


        window.add(message);
        window.row().pad(25,25,25,25);
        window.add(level1);
        window.row().pad(25,25,25,25);
        window.add(level2);
        window.setPosition(stage.getWidth() / 2 - 300, stage.getHeight() /2 - 300);




        /*table = new Table();



        table.setFillParent(true);

        //table.center().center();


        table.add(message);
        table.row();
        table.add(lscore);
        table.row();
        table.add(button); */



        //stage.addActor(table);
        stage.addActor(window);
        Gdx.input.setInputProcessor(stage);

    }

    public void render(float delta){

        stage.act();
        stage.draw();






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
        stage.dispose();


    }

    public void resize(){


    }
}