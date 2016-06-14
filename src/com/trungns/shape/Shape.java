package com.trungns.shape;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.trungns.bullet.Bullet;
import com.trungns.tank.TankDad;

public class Shape {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int type;
	protected Image img;
	public static final int BRICK = 0;
	public static final int	WATER = 1;
	public static final int	TREE = 2;
	public static final int GRASS = 3;
	public static final int CONCRETE = 4;
	public static final int HEART = 5;
	public static final int BIRD = 6;
	public static final int BIRD_WIDTH = 40;
	public static final int BIRD_HETGHT = 40;
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	public Shape(int x, int y, int width, int height, int type, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.img = img;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getType() {
		return type;
	}
	public Image getImg() {
		return img;
	}
	public void draw(Graphics2D g2d){
		g2d.drawImage(img, x, y, width, height, null);
	}
	//protected abstract boolean isIntersectBullet(Bullet bullet);
	public boolean isIntersectBullet(Bullet bullet) {
		// TODO Auto-generated method stub
		int newXBullet = bullet.getX();
		int newYBullet = bullet.getY();
		switch (bullet.getOrient()) {
		case Bullet.LEFT:
			newXBullet -=TankDad.DISTANCE_TO_COLLISION;
			break;
		case Bullet.RIGHT:
			newXBullet +=TankDad.DISTANCE_TO_COLLISION;
			break;
		case Bullet.UP:
			newYBullet -=TankDad.DISTANCE_TO_COLLISION;
			break;
		case Bullet.DOWN:
			newYBullet +=TankDad.DISTANCE_TO_COLLISION;
			break;
		default:
			break;
		}
		Rectangle recBrick = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle recBullet = new Rectangle(newXBullet, newYBullet, bullet.getWidth(), bullet.getHeight());
		return recBullet.intersects(recBrick);
	}
}
