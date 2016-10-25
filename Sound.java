package mini;

import java.applet.Applet;
import java.applet.AudioClip;

/*
 * Disclaimer: the sound clips used here are from the minicraft file except for the background.wav
 */

public class Sound {
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
	public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("background.wav"));
}
