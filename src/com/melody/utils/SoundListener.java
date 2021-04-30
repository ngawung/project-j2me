package com.melody.utils;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

public class SoundListener implements PlayerListener {

	public SoundListener() {
		// TODO Auto-generated constructor stub
	}

	public void playerUpdate(Player player, String event, Object eventData) {
		if (event.equals(END_OF_MEDIA)) {
			try {
				player.stop();
				
				// figure out a delay
				
				player.start();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
