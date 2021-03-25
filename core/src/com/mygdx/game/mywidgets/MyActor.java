package com.mygdx.game.mywidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public abstract class MyActor extends Actor {

    public interface MyActorListener {
        void call();
    }

    public Animation<TextureRegion> animation;
    public float stateTime;
    public Body body;

    public MyActor(Fixture fixture){
        this.body = fixture.getBody();
        body.setUserData(this);
        defineBody();
        define(fixture);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        setPosition(body.getPosition().x, body.getPosition().y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(this.animation == null) return;

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(animation.getKeyFrame(stateTime), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void addListener(MyActorListener myActorListener){
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                myActorListener.call();
                return false;
            }
        });
    }

    // TODO: define fixture en subclass
    public void define(Fixture fixture){
        Filter filter = new Filter();
        define(filter);
        fixture.setFilterData(filter);
    }

    public void define(Filter filter){}
    public void defineBody(){}
}
