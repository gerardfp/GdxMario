package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Config;
import net.dermetfan.gdx.physics.box2d.Box2DMapObjectParser;

public class Map extends Actor {
    TiledMap map;
    MapRenderer mapRenderer;
    OrthographicCamera camera;

    public Map(OrthographicCamera camera) {
        this.camera = camera;
        map = new TmxMapLoader().load("maps/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, Config.UNIT_SCALE);
    }

    public void loadObjects(MyWorld myWorld) {

        // Añadir dependencia -> api "com.github.tommyettinger:libgdx-utils-box2d:0.13.7"  (mirar build.gradle)

        // En Tiled hay que poner Nombre -> mario, goomba,... y Type -> object
        // Solo acepta objetos de tipo rectangulo, punto, elipse o triangulo
        // No importa en que capa esten
        Box2DMapObjectParser box2DMapObjectParser = new Box2DMapObjectParser(); // TODO: UnitScale

        box2DMapObjectParser.setListener(new Box2DMapObjectParser.Listener.Adapter() {
            @Override
            public void created(Fixture fixture, MapObject mapObject) {
                super.created(fixture, mapObject);
                switch (mapObject.getName()) {
                    case "mario":
                        myWorld.addMario(fixture);
                        break;
                    case "goomba":
                        myWorld.addGoomba(fixture);
                        break;
                    case "coin":
                        myWorld.addCoin(fixture);
                        break;
                    case "ground":
                        myWorld.addGround(fixture);
                        break;
                }
            }
        });

        box2DMapObjectParser.load(myWorld.world, map);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        mapRenderer.setView(camera);
        mapRenderer.render();
    }
}