package com.mustacheman.game.Screens;

import com.MainClass.game.MainClass;
import com.Scenes.Hud;
import com.Tools.B2WorldCreator;
import com.Tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.MainClass.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.*;
import com.sprites.Coin;
import com.sprites.Enemy;
import com.sprites.MoustacheMan;
import com.sprites.lilMon;

import sun.applet.Main;

public class PlayScreen implements Screen{
    private OrthographicCamera gamecam;
    private Viewport gameport;



    TextureAtlas atlas;
    com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> run;
    Hud hud;
    private World world;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private MoustacheMan player;
    private AssetManager manager;
    private Music music;
    private Coin coin;
    public int currentjump;
    private B2WorldCreator creator;
    public boolean endlevel = false;
    private GameOver gameover;
    public boolean gameoverb;
    public boolean gameovermade = false;
    public boolean isalive;
;


    private MainClass game;


    public PlayScreen(MainClass game){



        gamecam = new OrthographicCamera();
        gameoverb = false;


        gameport = new FitViewport(MainClass.V_Width / MainClass.PPM, MainClass.V_Height / MainClass.PPM ,gamecam);
        //gameport = new ExtendViewport(Gdx.graphics.getWidth() / MainClass.PPM, Gdx.graphics.getHeight() /MainClass.PPM ,gamecam);


        this.game = game;
        game.batch = new SpriteBatch();
        atlas = new TextureAtlas("RunJumpKoRoll.atlas");
        run = new com.badlogic.gdx.graphics.g2d.Animation(1/30f, atlas.findRegions("run/run"), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MainClass.PPM);







        gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2, 0);
        world = new World(new Vector2(0,-10 ), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this, hud);


        player = new MoustacheMan(this); // Make sure world is made before players and objects are initialized. Else it will erase those objects and give a null pointer exception
        world.setContactListener(new WorldContactListener(this));

        manager = new AssetManager();
        manager.load("sounds/music.ogg", Music.class);
        manager.finishLoading();

        music = manager.get("sounds/music.ogg", Music.class);

        //music.setLooping(true);
        //music.play();
        b2dr.setDrawBodies(false);  //Set to true to stop showing debug lines












    }

    @Override
    public void show() {

    }

    public TextureAtlas getTextureAtlas(){
        return atlas;

    }
    public void handleInput(float dt) {
        if (player.dead == false ) {

            if ((Gdx.input.isKeyJustPressed(Input.Keys.UP) || hud.isUpPressed() && (player.b2body.getLinearVelocity().y <= 2)) && currentjump < 1) {
                player.b2body.applyLinearImpulse(new Vector2(0, 6f), player.b2body.getWorldCenter(), true);
                currentjump += 1;
            }
            if (((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || hud.isRightPressed()) && (player.b2body.getLinearVelocity().x <= 4))) {
                player.b2body.applyLinearImpulse(new Vector2(2f, 0), player.b2body.getWorldCenter(), true);
            }
            if (((Gdx.input.isKeyPressed(Input.Keys.LEFT) || hud.isLeftPressed()) && (player.b2body.getLinearVelocity().x >= -4))) {
                player.b2body.applyLinearImpulse(new Vector2(-2f, 0), player.b2body.getWorldCenter(), true);
            }
        }




    }







    public void update(float dt) {

        handleInput(dt);




        world.step(1/60f,6,2);

        if (player.b2body.getPosition().y < 0){gameoverb = true; isalive = false; }

        if (hud.worldTimer == 0){gameoverb = true;}

        if (gameoverb && !gameovermade){
            gameover = new GameOver(game.batch, hud.score(), game, isalive);
            gameovermade = true;

        }



        //coin.update(dt);
        if (!gameoverb){gamecam.position.x = player.b2body.getPosition().x;}



        gamecam.update();
        renderer.setView(gamecam);

        player.update(dt);

        for (Enemy enemy : creator.getMonsters()){
            enemy.update(dt);
        }



        hud.update(dt);

        endlevel = player.dead;








    }

    @Override
    public void render(float delta) {


        update(delta);


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        renderer.render();

        hud.hudStage.draw();
        game.batch.setProjectionMatrix(hud.hudStage.getCamera().combined);



        //Hudstage ProjectionMatrix has be be set before gamecam should
        //Do some research later on why this is, and it affected MoustacheMan.java
        //Try to understand this section better in general








        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();


        player.draw(game.batch);
        for (Enemy enemy : creator.getMonsters()){
            enemy.draw(game.batch);
        }

        game.batch.end();

        b2dr.render(world, gamecam.combined);

        if (gameoverb) {gameover.render(delta);
        gameover.gameoStage.draw();
        game.batch.setProjectionMatrix(gameover.gameoStage.getCamera().combined);}







    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
        hud.resize(width,height);
        if (gameoverb) { gameover.gameoStage.getViewport().update(width, height);}

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

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
    }

    public PlayScreen getScreen(){
        return  this;
    }



    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        gameover.dispose();



    }
}
