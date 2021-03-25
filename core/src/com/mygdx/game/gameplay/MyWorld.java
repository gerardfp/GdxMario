package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public class MyWorld extends Group {

    OrthographicCamera camera;

    public static final short GROUND_BIT = 1;
    public static final short COIN_BIT = 2;
    public static final short GOOMBA_BIT = 4;
    public static final short MARIO_BIT = 8;

    private final Box2DDebugRenderer debugRenderer;

    // TODO: necessaries?
    public Mario mario;
    public List<Goomba> goombas = new ArrayList<>();
    public List<Coin> coins = new ArrayList<>();


    World world;

    public MyWorld(OrthographicCamera camera){
        this.camera = camera;

        world = new World(new Vector2(0,-80), true);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
                switch (cDef){
                    case MARIO_BIT | GROUND_BIT:
                        break;
                    case GOOMBA_BIT | GROUND_BIT:
                        break;
                }
            }

            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        });

        Map map = new Map(camera);
        map.loadObjects(this);
        addActor(map);
    }


    public void addMario(Fixture fixture) {
        addActor(mario = new Mario(fixture));
    }

    public void addGoomba(Fixture fixture) {
        Goomba goomba = new Goomba(fixture);
        goombas.add(goomba);
        addActor(goomba);
    }

    public void addCoin(Fixture fixture) {
        Coin coin = new Coin(fixture);
        coins.add(coin);
        addActor(coin);
    }

    public void addGround(Fixture fixture) {
        fixture.setDensity(0.5f);
        fixture.setFriction(0.1f);
        fixture.setRestitution(0.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        world.step(delta, 6, 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        debugRenderer.render(world, camera.combined);
    }

    // TODO: ???
    public void init() {
        for(Goomba goomba:goombas){
            goomba.body.setLinearVelocity(new Vector2(10,0));
        }
    }
}
