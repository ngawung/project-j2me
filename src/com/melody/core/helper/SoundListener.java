package com.melody.core.helper;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

import com.melody.core.MainEngine;
import com.melody.core.SoundManager;
import com.melody.display.MText;

public class SoundListener implements PlayerListener {
	private SoundManager _soundManager;

	public SoundListener(SoundManager soundManager) {
		_soundManager = soundManager;
	}

	public void playerUpdate(Player player, String event, Object eventData) {
		
	}

}
