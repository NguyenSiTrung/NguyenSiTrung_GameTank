package com.trungns.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Tank extends TankDad{
	
	public static final int TANK_HP = 100;
	public static final int TANK_BEGIN_X = 250;
	public static final int TANK_BEGIN_Y = 200;

	public Tank(int x, int y, int width, int height, int orient, int hp,
			int speedMove, int speedFire, Image[] img) {
		super(x, y, width, height, orient, hp, speedMove, speedFire, img);
		// TODO Auto-generated constructor stub
	}

}
