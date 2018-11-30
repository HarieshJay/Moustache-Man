package com.Scenes;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
import com.mustacheman.game.Screens.MainMenu;
import com.mustacheman.game.Screens.PlayScreen;

import sun.applet.Main;

public class Menu implements Disposable {



    private MainClass game;

    public Stage stage;
    private Viewport viewport;

    TextButton level1;
    TextButton level2;

    Skin skin;

    Label message;
    Label message2;

    Window window;
    MainMenu screen;


    public Menu(MainClass game, MainMenu screen) {


        this.game = game;

        this.screen = screen;


        onCreate();






    }



    public void onCreate(){

        skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
        window = new Window("Level Selection", skin);

        //redo = new TextButton("restart", skin);


        viewport = new FitViewport(MainClass.V_Width, MainClass.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        level1 = new TextButton("Easy", skin);
        level2 = new TextButton("Hard", skin);

        level1.setSize(150, 150);
        level2.setSize(150, 150);

        message = new Label("Choose a level", skin);
        message2 = new Label("Have Fun", skin);

        message.setFontScale(2);
        message2.setFontScale(2);

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
        window.add(message2);
        window.row().pad(25,25,25,25);
        window.add(level1).size(150,100);
        window.row().pad(25,25,25,25);
        window.add(level2).size(150,100);
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

        skin.dispose();
        stage.dispose();


    }

    public void resize(int width, int length){
        viewport.update(width, length);


    }
}