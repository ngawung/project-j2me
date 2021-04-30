package com.melody.utils;

import java.io.IOException;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

public class SoundManager {
	public Player player;

	public SoundManager() {
		
	}
	
	public void load(String filename) {
		try {
			String type = "";
			
			switch(filename.substring(-3)) {
				case "wav": type = "audio/x-wav";
				case "mp3": type = "audio/mpeg";
				case "mid": type = "audio/midi";
				case "amr": type = "audio/amr";
			}
			
			player = Manager.createPlayer(getClass().getResourceAsStream(filename), type);
			player.realize();
			player.prefetch();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unload() {
		try {
			player.stop();
			player.deallocate();
			player.close();
			player = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			player.setMediaTime(0);
			player.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(boolean unloadData) {
		try {
			player.stop();
			if (unloadData) unload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loop() {
		stop(false);
		player.addPlayerListener(new SoundListener());
		play();
	}

}
