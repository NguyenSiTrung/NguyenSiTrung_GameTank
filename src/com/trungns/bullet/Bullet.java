package com.trungns.bullet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import com.trungns.shape.Shape;

public class Bullet {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int WIDTH_BULLET = 8;
	public static final int HEIGHT_BULLET = 8;
	public static final int DISTANCE_TO_COLLISION = 2;
	public static final int DAME = 50;
	private int x, y, width, height, orient, speed;
	private Image img;
	private int dame;

	// private int damage;

	public Bullet(int x, int y, int width, int height, int orient, int speed,
			Image img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orient = orient;
		this.speed = speed;
		this.img = img;
		// this.damage = damage;
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

	public int getSpeed() {
		return speed;
	}

	public Image getImg() {
		return img;
	}

	public int getDama() {
		return dame;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(img, this.x, this.y, this.width, this.height, null);
	}

	public void moveBullet(int time) {
		if (time % speed == 0) {
			switch (orient) {
			case LEFT:
				x = x - 1;
				break;
			case RIGHT:
				x = x + 1;
				break;
			case UP:
				y = y - 1;
				break;
			case DOWN:
				y = y + 1;
				break;
			default:
				break;
			}
		}
	}

	public boolean bulletIsIntersectShape(Shape shape) {
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
		Rectangle rectBullet = new Rectangle(newX, newY, width, height);
		Rectangle rectShape = new Rectangle(shape.getX(), shape.getY(),
				shape.getWidth(), shape.getHeight());
		return rectBullet.intersects(rectShape);
	}

	public boolean bulletIntersectBulletDifferrent(Bullet bullet) {
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
		Rectangle rectBullet = new Rectangle(newX, newY, width, height);
		Rectangle rectBulletDifferrent = new Rectangle(bullet.getX(),
				bullet.getY(), bullet.getWidth(), bullet.getHeight());
		return rectBullet.intersects(rectBulletDifferrent);
	}

	public void chanImage(Image img) {
		this.img = img;
	}
}
