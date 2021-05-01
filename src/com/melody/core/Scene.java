package com.melody.core;

import java.util.Vector;

import com.melody.display.Mobject;
import com.melody.input.Input;

public abstract class Scene {
	public  MainEngine _e;
	
	private Vector _child;
	private boolean _initialized = false;
	
	public Scene() {
		_e = MainEngine.get_instance();
		_child = new Vector();
	}
	
	// called by engine
	public final void preInit() {
		initialize();
		_initialized = true;
	}
	
	public final void preUpdate(long dt) {
		// update physic
		
		// update child
		for (int i=0; i<_child.size(); i++) {
			((Mobject)_child.elementAt(i)).update(dt);
		}
		
		// update scene
		update(dt);
	}
	
	// override this
	public abstract void initialize();
	public abstract void update(long dt);
	public abstract void destroy();
	
	// Child Operation!
	
	// ADD
	public final void addChild(Mobject object) {
		object.initialize();
		_child.addElement(object);
	}
	
	// REMOVE
	public final void removeChild(Mobject object) {
		object.destroy();
		_child.removeElement(object);
	}
	
	public final void removeChildAt(int index) {
		if (index > _child.size() || index < 0) return;
		
		((Mobject)_child.elementAt(index)).destroy();
		_child.removeElementAt(index);
	}
	
	public final void removeChildAll() {
		for (int i=0; i<_child.size(); i++) {
			((Mobject)_child.elementAt(i)).destroy();
		}
		
		_child.removeAllElements();
	}
	
	// GET
	public final Mobject getChildAt(int index) {
		if (index > _child.size() || index < 0) return null;
		
		return ((Mobject)_child.elementAt(index));
	}
	
	public final Mobject getChildByName(String name) {
		for (int i=0; i<_child.size(); i++) {
			if (((Mobject)_child.elementAt(i)).name.equals(name)) {
				return ((Mobject)_child.elementAt(i));
			}
		}
		
		System.out.println("not found " + name);
		return null;
	}
	
	// GET & SET
	
	public Vector get_childrens() {
		return _child;
	}
	
	public Input get_input() {
		return _e.get_gameRoot().get_input();
	}
	
	public SaveManager get_saveManager() {
		return _e.get_saveManager();
	}
	
	public SoundManager get_soundManager() {
		return _e.get_soundManager();
	}
	
	public int get_width() {
		return _e.get_gameRoot().getWidth();
	}
	
	public int get_height() {
		return _e.get_gameRoot().getHeight();
	}
	
	public boolean get_initialized() {
		return _initialized;
	}
	
	public void set_backgroundColor(int color) {
		_e.get_gameRoot().backgroundColor = color;
	}

}
