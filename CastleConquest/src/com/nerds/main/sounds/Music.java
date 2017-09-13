package com.nerds.main.sounds;



import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Music {
	
	private String path;
	private AudioInputStream audioInputStream;
	private Clip clip;

	public Music(String path){
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	        clip = AudioSystem.getClip();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void play(){
		 try {
			clip.open(audioInputStream);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pause(){
		clip.stop();
	}
	
	public void stop(){
		clip.stop();
		clip.close();
	}
	
}
