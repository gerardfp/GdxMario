package com.mygdx.game.mywidgets;

import com.badlogic.gdx.Game;
import com.mygdx.game.LoadScreen;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		setScreen(new LoadScreen(MyGdxGame.this));
	}
}