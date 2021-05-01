package com.melody.core;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Image;

import com.melody.display.Mimage;
import com.melody.display.Texture;

public class AssetManager {
	
	private MainEngine _e;
	private Vector _textureLoaded = new Vector();

	public AssetManager() {
		_e = MainEngine.get_instance();
	}
	
	public void loadTexture(String name, String path) {
		try {
			Texture tex = new Texture(name, Image.createImage(path));
			_textureLoaded.addElement(tex);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getTextureId(String name) {
		for (int i=0; i<_textureLoaded.size(); i++) {
			if (((Texture)_textureLoaded.elementAt(i)).get_name().equals(name))
				return i;
		}
		
		return -1;
	}
	
	public Texture getTextureFromId(int textureId) {
		if (textureId < 0 || textureId > _textureLoaded.size()) return null;
		
		return (Texture)_textureLoaded.elementAt(textureId);
	}
	
	public boolean removeTexture(String name) {
		for (int i=0; i<_textureLoaded.size(); i++) {
			if (((Texture)_textureLoaded.elementAt(i)).get_name().equals(name)) {
				_textureLoaded.removeElementAt(i);
				requestReIndex();
				return true;
			}
		}
		
		return false;
	}
	
	public void requestReIndex() {
		Vector child = _e.get_gameRoot().get_scene().get_childrens();
		for (int i=0; i<child.size(); i++) {
			
			// request reIndex for displayObject
			if (child.elementAt(i) instanceof Mimage) {
				
			}
			
		}
	}

}
