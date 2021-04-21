package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.mywidgets.MyGdxGame;
import com.mygdx.game.mywidgets.MyScreen;

public class LoadScreen extends MyScreen {

    boolean dataloaded = false;

    public LoadScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        (S.assets = new Assets()).load();

        MyNet.get("Programming?type=single&amount=2", response -> {
            System.out.println(response.get("jokes").get(0).getString("joke"));
            dataloaded = true;
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!S.assets.update() || !dataloaded) return;

//        setScreen(new MenuScreen(game));
        setScreen(new GameScreen(game));
    }
}