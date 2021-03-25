package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.Assets;
import com.mygdx.game.mywidgets.MyActor;

public class Coin extends MyActor {

    public Coin(Fixture fixture) {
        super(fixture);
        setSize(8,14);

        animation = Assets.getAnimation("coin", 0.1f, Animation.PlayMode.LOOP);
    }

    @Override
    public void define(Filter filter) {

    }


}
