package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen {
    Batch batch;

    Texture background;

    private MainClass game;

    public Stage gameoStage;
    private Viewport viewport;



    TextButton level1;
    TextButton level2;

    Skin skin;
    int score;

    Label message;


    Window window;



    public MainMenu() {

        skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
        window = new Window("Game Over", skin);

        //redo = new TextButton("restart", skin);
        background = new Texture("landscape.png");


        viewport = new FitViewport(MainClass.V_Width, MainClass.V_Height, new OrthographicCamera());
        gameoStage = new Stage(viewport, batch);


        level1 = new TextButton("Level 1", skin);
        level2 = new TextButton("Level 2", skin);

        message = new Label("Please Select a Level, Pick level 2 for a Headache", skin);



        level1.setSize(150, 150);

        message.setFontScale(2);

        level1.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen( game));
                return true;

            }


        });

        level2.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen( game));
                return true;

            }


        });


        window.setSize(600,600);


        window.add(message);
        window.row().pad(25,25,25,25);
        window.add(level1);
        window.row().pad(25,25,25,25);
        window.add(level2);
        window.setPosition(gameoStage.getWidth() / 2 - 300, gameoStage.getHeight() /2 - 300);




        /*table = new Table();



        table.setFillParent(true);

        //table.center().center();


        table.add(message);
        table.row();
        table.add(lscore);
        table.row();
        table.add(button); */



        //gameoStage.addActor(table);
        gameoStage.addActor(window);
        Gdx.input.setInputProcessor(gameoStage);

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
