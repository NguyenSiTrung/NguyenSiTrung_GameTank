package main;

import com.trungns.sound.SoundManager;
import com.trungns.ui.GUI;


public class Main {
	public static void main(String[] args) {
		GUI test = new GUI();
		test.setVisible(true);
		//Map test = new Map();
		//test.redFileMap("D:/map1.txt");
		SoundManager sound = new SoundManager();
		sound.getSoundBG().play();
	}
}
