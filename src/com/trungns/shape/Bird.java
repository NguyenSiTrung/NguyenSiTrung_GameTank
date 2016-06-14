package com.trungns.shape;

import java.awt.Graphics2D;
import java.awt.Image;

import com.trungns.bullet.Bullet;
import com.trungns.ui.GUI;

public class Bird extends Shape{
	
	public static final int WIDTH_BIRD = 60;
	public static final int HEIGHT_BIRD = 60;
	public static final int HP_BIRD = 200;
	public static final int BIRD_X = (GUI.WIDTH_GAME - WIDTH_BIRD)/2;
	public static final int BIRD_Y = GUI.HEIGHT_GAME - HEIGHT_BIRD - 30;
	private int hp;
	public Bird(int x, int y, int width, int height, int type, Image img,int hp) {
		super(x, y, width, height, type, img);
		// TODO Auto-generated constructor stub
		Bird.this.hp = hp;
	}

	
	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}
	public void birdIsShot(){
		this.hp = hp - Bullet.DAME;
	}
	public boolean birdIsDie(){
		if(this.hp == 0){
			return true;
		}
		return false;
	}
}
