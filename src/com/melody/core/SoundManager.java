package com.melody.core;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import com.melody.display.MText;
import com.melody.enums.SoundRequest;

public class SoundManager implements PlayerListener {
	private Player _player;
	private SoundRequest _request = SoundRequest.NONE;
	
	private final int _delay = 50;
	private int _currentDelay = 0;
	private boolean _isPlaying = false;
	
	public boolean loop = false;

	public SoundManager() {
		
	}
	
	// called by engine
	public void update(long dt) {
		if (_player == null || _request.getValue() == SoundRequest.NONE.getValue()) return;
		
		if (SoundRequest.PLAY.getValue() == _request.getValue()) {
			if (_currentDelay >= _delay) {
				try {
					_player.realize();
				} catch (Exception e) {}
				try {
					_player.prefetch();
				} catch (Exception e) {}
				try {
					_player.start();
				} catch (Exception e) {}
				
				_currentDelay = 0;
				_isPlaying = true;
				_request = SoundRequest.NONE;
				
				System.out.println("REQEST PLAY");
			} else {
				System.out.println("skip");
				_currentDelay += dt;
			}
		}
		
		else if (SoundRequest.STOP.getValue() == _request.getValue()) {
			try {
				_player.stop();
			} catch (Exception e) {}
			try {
				_player.deallocate();
			} catch (Exception e) {}
			
			_isPlaying = false;
			_request = SoundRequest.NONE;
			System.out.println("REQEST STOP");
		}
		
		else if (SoundRequest.REPLAY.getValue() == _request.getValue()) {
			try {
				_player.stop();
			} catch (Exception e) {}
			try {
				_player.deallocate();
			} catch (Exception e) {}
			
			_isPlaying = false;
			_request = SoundRequest.PLAY;
			System.out.println("REQEST REPLAY");
		}
	}
	
	public void load(String filename) {
		try {
			String temp = filename.substring(filename.length() - 3);
			String type = "";
			
			if (temp.equals("wav")) type = "audio/x-wav";
			else if (temp.equals("mp3")) type = "audio/mpeg";
			else if (temp.equals("mid")) type = "audio/midi";
			else if (temp.equals("amr")) type = "audio/amr";
			
			System.out.println(temp);
			System.out.println(type);
			
			_player = Manager.createPlayer(getClass().getResourceAsStream(filename), type);
			_player.addPlayerListener(this);
			
		} catch (Exception e) {
			// return null
			e.printStackTrace();
		}
	}
	
	public void unload() {
		try {
			_player.stop();
			_player.deallocate();
			_player.removePlayerListener(this);
			_player.close();
			_player = null;
			
			_request = SoundRequest.NONE;
		} catch (Exception e) {
			// return null
		}
	}
	
	public void play() {
		System.out.println(_request.getKey());
		if (!_isPlaying) _request = SoundRequest.PLAY;
		else _request = SoundRequest.REPLAY;
	}
	
	public void stop() {
		_request = SoundRequest.STOP;
	}

	public void playerUpdate(Player player, String event, Object eventData) {
		((MText)MainEngine.get_instance().get_gameRoot().get_scene().getChildByName("text")).text = event;
		System.out.println(event == PlayerListener.END_OF_MEDIA);
		if (event.equals(END_OF_MEDIA)) {
			stop();
			
			if (loop) play();
		}		
	}

}
