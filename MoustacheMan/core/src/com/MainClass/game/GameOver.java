package com.MainClass.game;

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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameOver implements Disposable {
    Batch batch;
    Texture texture;
    private MainClass game;
    BitmapFont font;
    public Stage gameoStage;
    private Viewport viewport;
    Table table;
    Texture restart;
    TextButton button;
    TextButton redo;
    Skin skin;
    int score;
    Label message;
    Label lscore;
    boolean isWin;
    Window window;
    TextButton levelSelect;
    public String level;

    public GameOver(SpriteBatch batch, int score, MainClass game, boolean isWin, String level) {


        this.game = game;
        this.batch = batch;
        this.score = score;
        this.isWin = isWin;
        this.level = level;
        onCreate();


    }



    public void onCreate(){

        skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));
        window = new Window("Game Over", skin);

        //redo = new TextButton("restart", skin);
        texture = new Texture("landscape.png");
        font = new BitmapFont(Gdx.files.internal("font.fnt"),
                Gdx.files.internal("font.png"), false);

        viewport = new FitViewport(MainClass.V_Width, MainClass.V_Height, new OrthographicCamera());
        gameoStage = new Stage(viewport, batch);


        if (isWin){message = new Label("YOU WON!!", skin);}
        if (!isWin){message = new Label("OH NO! NICE TRY", skin);}


        button = new TextButton("Restart Level", skin);
        levelSelect = new TextButton("Different Level", skin);

        lscore = new Label("You Scored " + Integer.toString(score) + " Points!", skin);



        button.setSize(150, 150);
        message.setFontScale(2);
        lscore.setFontScale(2);
        button.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen( game, level));
                return true;

            }


        });

        levelSelect.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenu(game));

                return true;

            }


        });


        window.setSize(600,600);


        window.add(message);
        window.row().pad(25,25,25,25);
        window.add(lscore);
        window.row().pad(25,25,25,25);
        window.add(button).size(175,100);
        window.row().pad(25,25,25,25);
        window.add(levelSelect).size(175,100);
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

    public void resize(){

    }
}