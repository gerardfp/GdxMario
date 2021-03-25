package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Assets;
import com.mygdx.game.mywidgets.MyActor;

public class Goomba extends MyActor {

    public Goomba(Fixture fixture) {
        super(fixture);
        setSize(10,10);

        animation = Assets.getAnimation("goomba_walk", 1, Animation.PlayMode.NORMAL);
    }

    @Override
    public void define(Filter filter) {
        filter.categoryBits = MyWorld.GOOMBA_BIT;
        filter.maskBits = MyWorld.GROUND_BIT |
                        MyWorld.MARIO_BIT;
    }

    @Override
    public void defineBody() {
        body.setType(BodyDef.BodyType.DynamicBody);
    }
}
