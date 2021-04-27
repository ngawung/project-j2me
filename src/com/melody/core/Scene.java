package com.melody.core;

import java.util.Vector;

import com.melody.display.Mobject;
import com.melody.input.Input;

public class Scene {
	public MainEngine _e;
	public Vector child;
	
	public Scene() {
		_e = MainEngine.getInstance();
		child = new Vector();
	}
	
	public final void preInit() {
		initialize();
	}
	
	public final void preUpdate(long dt) {
		// update physic
		
		// update child
		for (int i=0; i<child.size(); i++) {
			((Mobject)child.elementAt(i)).update();
		}
		
		// update scene
		update(dt);
	}
	
	public final void render() {
		
	}
	
	// override this
	
	public void initialize() {
		
	}
	
	public void update(long dt) {
		
	}
	
	public void destroy() {
		
	}
	
	// Child Operation!
	
	// ADD
	public final void addChild(Mobject object) {
		object.initialize();
		child.addElement(object);
	}
	
	// REMOVE
	public final void removeChild(Mobject object) {
		object.destroy();
		child.removeElement(object);
	}
	
	public final void removeChildAt(int index) {
		if (index > child.size() || index < 0) return;
		
		((Mobject)child.elementAt(index)).destroy();
		child.removeElementAt(index);
	}
	
	public final void removeChildAll() {
		for (int i=0; i<child.size(); i++) {
			((Mobject)child.elementAt(i)).destroy();
		}
		
		child.removeAllElements();
	}
	
	// GET
	public final Mobject getChildAt(int index) {
		if (index > child.size() || index < 0) return null;
		
		return ((Mobject)child.elementAt(index));
	}
	
	public final Mobject getChildByName(String name) {
		for (int i=0; i<child.size(); i++) {
			if (((Mobject)child.elementAt(i)).name == name) {
				return ((Mobject)child.elementAt(i));
			}
		}
		
		return null;
	}
	
	// GET & SET
	
	public Vector get_childrens() {
		return child;
	}
	
	public Input get_input() {
		return _e.get_gameRoot().input;
	}
	
	public int get_width() {
		return _e.get_gameRoot().getWidth();
	}
	
	public int get_height() {
		return _e.get_gameRoot().getHeight();
	}

}
