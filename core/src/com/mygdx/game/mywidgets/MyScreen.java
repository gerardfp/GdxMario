package com.mygdx.game.mywidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Config;

public class MyScreen implements Screen {
    public final MyGdxGame game;
    public final MyStage stage;
    public final Table table;
    public OrthographicCamera camera;
    public Viewport viewport;


    public MyScreen(MyGdxGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.position.set(Config.WIDTH /2, Config.HEIGHT /2, 0);
        viewport = new FitViewport(Config.WIDTH, Config.HEIGHT, camera);
        viewport.apply();

        Gdx.input.setInputProcessor(stage = new MyStage(viewport));
        table = new Table();
    }
    public void setScreen(MyScreen screen){
        Gdx.input.setInputProcessor(screen.stage);
        game.setScreen(screen);
    }
    @Override public void show() {}
    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.54f, 0.87f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();
    }
    @Override public void resize(int width, int height) {
        viewport.update(width ,height);
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}
