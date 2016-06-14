package com.trungns.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.trungns.bullet.Bullet;
import com.trungns.shape.Shape;
import com.trungns.ui.GUI;

public class TankDad {
	public static final int DISTANCE_TO_COLLISION = 2;
	private int x, y, width, height, orient, hp, speedMove, speedFire;
	private Image[] img = new Image[4];
	public static final int WIDTH_TANK = 33;
	public static final int HEIGHT_TANK = 33;
	public static final int ALL_ORIENT = 4;
	public TankDad(int x, int y, int width, int height, int orient, int hp,
			int speedMove, int speedFire, Image[] img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orient = orient;
		this.hp = hp;
		this.speedMove = speedMove;
		this.speedFire = speedFire;
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

	public int getOrient() {
		return orient;
	}

	public int getHp() {
		return hp;
	}

	public int getSpeedMove() {
		return speedMove;
	}

	public int getSpeedFire() {
		return speedFire;
	}

	public Image[] getImg() {
		return img;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setOrient(int orient) {
		this.orient = orient;
	}

	public void setSpeedMove(int speedMove) {
		this.speedMove = speedMove;
	}

	public void setSpeedFire(int speedFire) {
		this.speedFire = speedFire;
	}
	
	public void setImg(Image[] img) {
		this.img = img;
	}

	// protected abstract void
	public boolean isExit() {
		if (this.hp == 0) {
			return false;
		}
		return true;
	}

	public void draw(Graphics2D g2d) {
		switch (orient) {
		case Bullet.LEFT:
			g2d.drawImage(img[0], this.x, this.y, this.width, this.height, null);
			break;
		case Bullet.RIGHT:
			g2d.drawImage(img[1], this.x, this.y, this.width, this.height, null);
			break;
		case Bullet.UP:
			g2d.drawImage(img[2], this.x, this.y, this.width, this.height, null);
			break;
		case Bullet.DOWN:
			g2d.drawImage(img[3], this.x, this.y, this.width, this.height, null);
			break;
		default:
			break;
		}
	}

	public void speedMove(int time) {
		if (time % speedMove == 0) {
			switch (orient) {
			case Bullet.LEFT:
				x = x - 1;
				break;
			case Bullet.RIGHT:
				x = x + 1;
				break;
			case Bullet.UP:
				y = y - 1;
				break;
			case Bullet.DOWN:
				y = y + 1;
				break;
			default:
				break;
			}
		}
	}

	public boolean isFire(int time) {
		if (time % this.speedFire == 0) {
			return true;
		}
		return false;
	}

	public boolean isIntersectShape(Shape shape) {
		int newX = this.x;
		int newY = this.y;
		switch (orient) {
		case Bullet.LEFT:
			newX -= DISTANCE_TO_COLLISION;
			break;
		case Bullet.RIGHT:
			newX += DISTANCE_TO_COLLISION;
			break;
		case Bullet.UP:
			newY -= DISTANCE_TO_COLLISION;
			break;
		case Bullet.DOWN:
			newY += DISTANCE_TO_COLLISION;
			break;

		default:
			break;
		}
		Rectangle rectTank = new Rectangle(newX, newY, width, height);
		Rectangle rectShape = new Rectangle(shape.getX(), shape.getY(),
				shape.getWidth(), shape.getHeight());
		return rectTank.intersects(rectShape);
	}

	public boolean isScreenOut() {
		int newX = this.x;
		int newY = this.y;
		switch (orient) {
		case Bullet.LEFT:
			newX -= DISTANCE_TO_COLLISION;
			if (newX <= 0) {
				return true;
			}
			break;
		case Bullet.RIGHT:
			newX += DISTANCE_TO_COLLISION;
			if (newX + this.width >= GUI.WIDTH_GAME) {
				return true;
			}
			break;
		case Bullet.UP:
			newY -= DISTANCE_TO_COLLISION;
			if (newY <= 0) {
				return true;
			}
			break;
		case Bullet.DOWN:
			newY += DISTANCE_TO_COLLISION;
			if (newY + this.height + 24 >= GUI.HEIGHT_GAME) {
				return true;
			}
			break;

		default:
			break;
		}
		return false;
	}
	public boolean isIntersectTank(TankDad tank){
		int newX = this.x;
		int newY = this.y;
		switch (orient) {
		case Bullet.LEFT:
			newX -= DISTANCE_TO_COLLISION;
			break;
		case Bullet.RIGHT:
			newX += DISTANCE_TO_COLLISION;
			break;
		case Bullet.UP:
			newY -= DISTANCE_TO_COLLISION;
			break;
		case Bullet.DOWN:
			newY += DISTANCE_TO_COLLISION;
			break;

		default:
			break;
		}
		Rectangle rectTank1 = new Rectangle(newX, newY, width, height);
		Rectangle rectTank2 = new Rectangle(tank.getX(), tank.getY(),
				tank.getWidth(), tank.getHeight());
		return rectTank1.intersects(rectTank2);
	}
	public boolean isDie(){
		if(this.hp == 0){
			return true;
		}
		return false;
	}
	public boolean isIntersectBullet(Bullet bullet){
		boolean kt = false;
		int newX = this.x;
		int newY = this.y;
		switch (orient) {
		case Bullet.LEFT:
			newX -= DISTANCE_TO_COLLISION;
			break;
		case Bullet.RIGHT:
			newX += DISTANCE_TO_COLLISION;
			break;
		case Bullet.UP:
			newY -= DISTANCE_TO_COLLISION;
			break;
		case Bullet.DOWN:
			newY += DISTANCE_TO_COLLISION;
			break;

		default:
			break;
		}
		Rectangle rectTank = new Rectangle(newX, newY, width, height);
		Rectangle rectBullet = new Rectangle(bullet.getX(), bullet.getY(),
				bullet.getWidth(), bullet.getHeight());
		return rectTank.intersects(rectBullet);
	}
	public void tankIsShot(){
		this.hp = hp - Bullet.DAME;
	}
	public void changeOrient(int newOrient) {
		this.orient = newOrient;
	}
	
}
