package com.trungns.tank;

import java.awt.Image;
import java.util.Random;

import com.trungns.bullet.Bullet;
import com.trungns.shape.Bird;
import com.trungns.ui.GUI;

public class TankAuto extends TankDad{
	private int smart;
	public static final int MAX_SMART = 100;
	public static final int TANK_AUTO_HP = 50;
	public static final int NUMBER_TANKAUTO = 20;
	public static final int TANK_THONG_MINH = 70;
	public TankAuto(int x, int y, int width, int height, int orient, int hp,
			int speedMove, int speedFire, Image[] img, int smart) {
		super(x, y, width, height, orient, hp, speedMove, speedFire, img);
		// TODO Auto-generated constructor stub
		TankAuto.this.smart = smart;
	}
	
	public int getSmart() {
		
		return smart;
	}


	public void setSmart(int smart) {
		this.smart = smart;
	}

	public int randomChangeOrient(){
		Random rdOrient = new Random();
		int newOrient = Bullet.RIGHT;
		if(this.smart < TANK_THONG_MINH){
			newOrient = rdOrient.nextInt(TankDad.ALL_ORIENT);
			if(newOrient == TankAuto.this.getOrient()){
				return this.randomChangeOrient();
			} 
			return newOrient;
		}
		else{
			if(this.getY()<(GUI.HEIGHT_GAME - WIDTH_TANK)){
				newOrient = rdOrient.nextInt(2)+2;
			}
			if(this.getY() == (GUI.HEIGHT_GAME - WIDTH_TANK) && this.getX()<Bird.BIRD_X){
				newOrient = Bullet.RIGHT;
			}
			if(this.getY() == (GUI.HEIGHT_GAME - WIDTH_TANK) && this.getX()>Bird.BIRD_X){
				newOrient = Bullet.LEFT;
			}
		}
		return newOrient;
	}
	public int randomOrientAppear(){
		Random rdOrientAppear = new Random();
		int newOrientAppear = rdOrientAppear.nextInt(TankDad.ALL_ORIENT);
		
		return newOrientAppear;
	}
	public int randomSmart(){
		Random rdSmart = new Random();
		int newSmart = rdSmart.nextInt(MAX_SMART)+1;
		
		return newSmart;
	}
}
