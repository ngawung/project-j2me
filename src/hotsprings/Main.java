package hotsprings;

import hotsprings.scene.MenuSelector;
import melody.core.MainEngine;


public class Main extends MainEngine {
	public Main() {
		super("HotspringsDemo");
		
		System.out.println("Initialize");
	}
	
	public void initialize() {
		setupGame(20, true);
	}
	
	public void handleGameReady() {
		get_gameRoot().set_scene(new MenuSelector());
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
