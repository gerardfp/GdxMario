package com.mygdx.game.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.Assets;
import com.mygdx.game.mywidgets.MyActor;

public class Mario extends MyActor {

    public Mario(Fixture fixture) {
        super(fixture);
        setSize(16,16);
        animation = Assets.getAnimation("mario_idle", 1, Animation.PlayMode.NORMAL);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // TODO: clase Constants
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            body.applyForce(0, 5000, 0, 0, true);
        }
    }

    @Override
    public void define(Filter filter) {
        filter.categoryBits = MyWorld.MARIO_BIT;
        filter.maskBits = MyWorld.GROUND_BIT | MyWorld.GOOMBA_BIT | MyWorld.COIN_BIT;
    }

    @Override
    public void defineBody() {
        body.setType(BodyDef.BodyType.DynamicBody);
    }
}
