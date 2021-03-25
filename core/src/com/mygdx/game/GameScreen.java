package com.mygdx.game;

import com.mygdx.game.gameplay.MyWorld;
import com.mygdx.game.mywidgets.MyGdxGame;
import com.mygdx.game.mywidgets.MyScreen;

public class GameScreen extends MyScreen {
	public GameScreen(MyGdxGame game) {
		super(game);
	}

	@Override
	public void show () {
		MyWorld myWorld = new MyWorld(camera);

		stage.addActor(myWorld);

		myWorld.init();
	}
}
